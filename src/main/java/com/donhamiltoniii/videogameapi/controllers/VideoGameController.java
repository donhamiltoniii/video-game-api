package com.donhamiltoniii.videogameapi.controllers;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donhamiltoniii.videogameapi.models.VideoGame;
import com.donhamiltoniii.videogameapi.models.VideoGameRepository;

@RestController
@RequestMapping("/api/video-games")
public class VideoGameController {

	@Resource
	VideoGameRepository videoGameRepo;

	@GetMapping({ "", "/" })
	public Collection<VideoGame> getVideoGames() {
		return videoGameRepo.findAll();
	}

	@GetMapping("/{id}")
	public VideoGame getVideoGame(@PathVariable() Long id) throws Exception {
		Optional<VideoGame> videoGameOptional = videoGameRepo.findById(id);

		if (videoGameOptional.isPresent()) {
			return videoGameOptional.get();
		}

		throw new Exception("That don't exist!");
	}

	@PostMapping({ "", "/" })
	public void createVideoGame(@RequestBody String body, HttpServletResponse response)
			throws JSONException, IOException {
		// Turn Request Body into a JSON Object
		JSONObject json = (JSONObject) JSONParser.parseJSON(body);

		// Get individual JSON properties
		String title = json.getString("title");
		String studio = json.getString("studio");
		Float rating = (Float) (float) json.getDouble("rating");

		videoGameRepo.save(new VideoGame(title, studio, rating));

		response.sendRedirect("/api/video-games");
	}

	@PatchMapping("/{id}")
	public void updateVideoGame(@PathVariable() Long id, @RequestBody Map<String, Object> updates,
			HttpServletResponse response) throws Exception {
		VideoGame videoGame;
		Optional<VideoGame> videoGameOptional = videoGameRepo.findById(id);

		if (videoGameOptional.isPresent()) {
			videoGame = videoGameOptional.get();
		} else {
			throw new Exception("No such Video Game");
		}

		System.out.println(updates);

		response.sendRedirect("/api/video-games/");
	}
}
