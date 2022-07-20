package com.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.PostDto;
import com.dto.PostDtoOnly;
import com.model.Post;
import com.repository.PostRepository;
import com.util.GenericResponse;
import com.util.ParametersApp;

@Service
public class PostService {
    private static final Logger log = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private PostRepository postRepository;

    public GenericResponse<String> savePost(PostDto postDto) {
        GenericResponse<String> response = new GenericResponse<>();
        try {
            Post post = new Post();
            post.setIdUser(postDto.getIduser());
            post.setTitle(postDto.getTitle());
            post.setContent(postDto.getContent());
            post.setCreatedOn(Instant.now());
            post.setUsername(postDto.getUsername());
            post.setUpdatedOn(Instant.now());
            post.setIdSeccion(postDto.getIdSecciones());
            postRepository.save(post);
            response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
            response.setObject("POST " + post.getId() + " guardado correctamente ");
            response.setStatus(ParametersApp.SUCCESSFUL.value());
        } catch (Exception e) {
            // TODO: handle exception
            log.error("ERROR", e);
            response.setStatus(ParametersApp.SERVER_ERROR.value());
        }
        return response;
    }

    public GenericResponse<List<PostDto>> listAllpost() {
        GenericResponse<List<PostDto>> response = new GenericResponse<>();
        try {
            List<PostDto> postDto = new ArrayList<>();
            for (Post post : postRepository.findAllByOrderByIdDesc()) {
                if (post != null) {
                    PostDto dto = new PostDto();
                    dto.setId(post.getId());
                    dto.setTitle(post.getTitle());
                    dto.setContent(post.getContent());
                    dto.setUsername(post.getUsername());
                    dto.setCreatedOn(post.getCreatedOn());
                    dto.setUpdatedOn(post.getUpdatedOn());
                    dto.setUser(post.getUsuario());
                    postDto.add(dto);
                } else {
                    response.setStatus(ParametersApp.SERVER_ERROR.value());
                }
            }
            response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
            response.setObject(postDto);
            response.setStatus(ParametersApp.SUCCESSFUL.value());

        } catch (Exception e) {
            // TODO: handle exception
            log.error("ERROR", e);
            response.setStatus(ParametersApp.SERVER_ERROR.value());
        }
        return response;
    }

    public GenericResponse<String> updatePost(PostDto postDto) {
        GenericResponse<String> response = new GenericResponse<>();
        try {
            Post post = postRepository.findById(postDto.getId()).get();
            if (post != null) {
                post.setTitle(postDto.getTitle());
                post.setContent(postDto.getContent());
                post.setUsername(postDto.getUsername());
                post.setIdUser(postDto.getIduser());
                post.setIdSeccion(postDto.getIdSecciones());
                postRepository.save(post);
                response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
                response.setObject("POST " + post.getId() + " actualizado correctamente");
                response.setStatus(ParametersApp.SUCCESSFUL.value());
            } else {
                response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
                response.setObject("NO SE ENCONTRO EL POST CON ID: " + postDto.getId());
                response.setStatus(ParametersApp.SUCCESSFUL.value());
            }
        } catch (Exception e) {
            log.error("ERROR: ", e);
            response.setStatus(ParametersApp.SERVER_ERROR.value());
        }
        return response;
    }

    public GenericResponse<PostDto> getPostById(Long idPost) {
        GenericResponse<PostDto> response = new GenericResponse<>();
        try {
            Optional<Post> post = postRepository.findById(idPost);
            if (post.isPresent()) {
                PostDto dto = new PostDto();
                dto.setId(post.get().getId());
                dto.setTitle(post.get().getTitle());
                dto.setContent(post.get().getContent());
                dto.setUsername(post.get().getUsername());
                dto.setUser(post.get().getUsuario());
                dto.setCreatedOn(post.get().getCreatedOn());
                dto.setUpdatedOn(post.get().getUpdatedOn());
                response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
                response.setObject(dto);
                response.setStatus(ParametersApp.SUCCESSFUL.value());
            } else {
                response.setMessage("POST NO ENCONTRADO");
                response.setStatus(ParametersApp.NOT_FOUND_RECORDS.value());
            }
        } catch (Exception e) {
            log.error("ERROR", e);
            response.setMessage(ParametersApp.SERVER_ERROR.getReasonPhrase());
            response.setStatus(ParametersApp.SERVER_ERROR.value());
        }
        return response;
    }

    // findAllBySeccion
    public GenericResponse<List<PostDto>> findAllBySeccion(int nombre) {
        GenericResponse<List<PostDto>> response = new GenericResponse<>();
        try {
            List<PostDto> postDto = new ArrayList<>();
            for (Post post : postRepository.findAllBySeccion(nombre)) {
                if (post != null) {
                    PostDto dto = new PostDto();
                    dto.setId(post.getId());
                    dto.setIdSecciones(post.getIdSeccion());
                    dto.setIduser(post.getIdUser());
                    dto.setTitle(post.getTitle());
                    dto.setContent(post.getContent());
                    dto.setUsername(post.getUsername());
                    dto.setCreatedOn(post.getCreatedOn());
                    dto.setUpdatedOn(post.getUpdatedOn());
                    dto.setUser(post.getUsuario());
                    postDto.add(dto);
                } else {
                    response.setStatus(ParametersApp.SERVER_ERROR.value());
                }
            }
            response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
            response.setObject(postDto);
            response.setStatus(ParametersApp.SUCCESSFUL.value());

        } catch (Exception e) {
            response.setStatus(ParametersApp.SERVER_ERROR.value());
            log.error("ERROR", e);
        }
        return response;
    }

    public GenericResponse<List<PostDtoOnly>> findAllBySeccionAdmin() {
        GenericResponse<List<PostDtoOnly>> response = new GenericResponse<>();
        try {
            List<PostDtoOnly> postDto = new ArrayList<>();
            for (Post post : postRepository.findAllBySeccionAdmin()) {
                if (post != null) {
                    PostDtoOnly dto = new PostDtoOnly();
                    dto.setId(post.getId());
                    dto.setIdSecciones(post.getIdSeccion());
                    dto.setIduser(post.getIdUser());
                    dto.setTitle(post.getTitle());
                    dto.setContent(post.getContent());
                    dto.setUsername(post.getUsername());
                    dto.setCreatedOn(post.getCreatedOn());
                    dto.setUpdatedOn(post.getUpdatedOn());
                    postDto.add(dto);
                } else {
                    response.setStatus(ParametersApp.SERVER_ERROR.value());
                }
            }
            response.setMessage(ParametersApp.SUCCESSFUL.getReasonPhrase());
            response.setObject(postDto);
            response.setStatus(ParametersApp.SUCCESSFUL.value());

        } catch (Exception e) {
            response.setStatus(ParametersApp.SERVER_ERROR.value());
            log.error("ERROR", e);
        }
        return response;
    }
}
