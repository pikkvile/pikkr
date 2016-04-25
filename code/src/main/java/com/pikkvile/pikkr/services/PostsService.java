package com.pikkvile.pikkr.services;

import com.pikkvile.pikkr.enums.PostType;
import com.pikkvile.pikkr.model.NewPost;
import com.pikkvile.pikkr.model.entities.Photo;
import com.pikkvile.pikkr.model.entities.Post;
import com.pikkvile.pikkr.repositories.PhotosRepository;
import com.pikkvile.pikkr.repositories.PostsRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

@Service
public class PostsService {

    @Value("${pikkr.files-path}")
    String filesPath;

    @Autowired
    PostsRepository postsRepository;
    @Autowired
    PhotosRepository photosRepository;

    @Autowired
    CategoriesService categoriesService;

    private static final int PAGE_SIZE = 5;
    private static final Sort SORT = new Sort(Sort.Direction.DESC, "created");

    public Page<Post> getAllPosts(Integer page) {
        PageRequest pageRequest = new PageRequest(page, PAGE_SIZE, SORT);
        return postsRepository.findAll(pageRequest);
    }

    public Page<Post> getPostsInCategory(String categoryName, Integer page) {
        PageRequest pageRequest = new PageRequest(page, PAGE_SIZE, SORT);
        return postsRepository.findByCategoryTitle(categoryName, pageRequest);
    }

    @Transactional
    public void createPost(NewPost newPost, Map<String, MultipartFile> fileMap) throws Exception {

        Date now = new Date();

        Post post = new Post();
        post.setCategory(categoriesService.getCategoryById(newPost.getCategoryId()));
        post.setCreated(now);
        post.setTitle(newPost.getTitle());
        post.setType(PostType.valueOf(newPost.getType()));

        post = postsRepository.save(post);

        for (MultipartFile multipartFile: fileMap.values()) {
            int position = Integer.parseInt(multipartFile.getName().replace("file", "")) - 1;
            String name = "post" + post.getId() + "-" + multipartFile.getOriginalFilename();
            OutputStream os = new FileOutputStream(filesPath + name);
            IOUtils.copy(multipartFile.getInputStream(), os);
            os.flush();
            os.close();
            Photo photo = new Photo();
            photo.setAdded(now);
            photo.setName(name);
            photo.setPosition(position);
            photo.setPostId(post.getId());
            post.getPhotos().add(photosRepository.save(photo));
        }

        postsRepository.save(post);
    }
}
