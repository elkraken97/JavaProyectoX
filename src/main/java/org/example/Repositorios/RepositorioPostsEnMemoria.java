package org.example.Repositorios;

import org.example.Modelos.Post;

import java.util.*;

public class RepositorioPostsEnMemoria implements RepositorioPostsInterface{

    private long count = 1;
    private final Map<Long,Post> posts = new HashMap<>();
    @Override
    public Post guardarPost(Post post) {

        if (post.getId()==-1){
            post.setId(count++);
        }

        posts.put(post.getId(),post);

        return post;
    }

    @Override
    public Optional<Post> buscarPostsPorID(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    @Override
    public List<Post> buscarPostsPorUsuario(long usrId) {
        return posts.values().stream().filter(post->post.getUsrId()==usrId).sorted(Comparator.comparing(Post::getCreacion).reversed()).toList();
    }

    @Override
    public List<Post> todosLosPosts() {
        return new ArrayList<>(posts.values());
    }

    @Override
    public boolean borrarPosts(long id) {
        return posts.remove(id)!=null;
    }
}
