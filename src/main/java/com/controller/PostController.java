package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.PostDto;
import com.dto.PostDtoOnly;
import com.service.PostService;
import com.util.GenericResponse;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("api/posts/")
@Tag(name = "MenuWS", description = "Control y mantenimiento de menus")
public class PostController {
	
	@Autowired
    private PostService postService;

    @PostMapping(path = "save")
    public ResponseEntity<GenericResponse<String>> guardarMenu( @RequestBody PostDto  postDto){
        return new ResponseEntity<GenericResponse<String>> (postService.savePost(postDto), HttpStatus.OK);
    }

    @GetMapping(path = "listar-post")
	public ResponseEntity<GenericResponse<List<PostDto>>> listarPost(){
		return new ResponseEntity<GenericResponse<List<PostDto>>>(postService.listAllpost(), HttpStatus.OK);
	}

    @GetMapping(path = "findPostById")
	public ResponseEntity<GenericResponse<PostDto>> findPostById(@RequestParam Long idPost){
		return new ResponseEntity<GenericResponse<PostDto>>(postService.getPostById(idPost), HttpStatus.OK);
	}

    @PutMapping(path = "updatePost")
	public ResponseEntity<GenericResponse<String>> updatePost(@RequestBody PostDto postDto){
		return new ResponseEntity<GenericResponse<String>>(postService.updatePost(postDto), HttpStatus.OK);
	}

	//findAllBySeccion
	@GetMapping(path = "findAllBySeccion")
	public ResponseEntity<GenericResponse<List<PostDto>>> findAllBySeccion(@RequestParam int nombre){
		return new ResponseEntity<GenericResponse<List<PostDto>>>(postService.findAllBySeccion(nombre), HttpStatus.OK);
	}

	@GetMapping(path = "findAllBySeccionAdmin")
	public ResponseEntity<GenericResponse<List<PostDtoOnly>>> findAllBySeccionAdmin(){
		return new ResponseEntity<GenericResponse<List<PostDtoOnly>>>(postService.findAllBySeccionAdmin(), HttpStatus.OK);
	}
}
