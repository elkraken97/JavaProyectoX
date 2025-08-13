package org.example.Control;

import org.example.Modelos.Post;
import org.example.Repositorios.RepositorioPostsInterface;

import java.util.List;

public class ControlListarPost {
    
    
    private final RepositorioPostsInterface repoPosts;


    public ControlListarPost(RepositorioPostsInterface repoPosts) {
        this.repoPosts = repoPosts;
    }
    
    
    public void listarFeedGeneral(){
        List<Post> posts = repoPosts.todosLosPosts();
        if (posts.isEmpty()) {
            System.out.println("NO hay posts por mostrar");
            return;
        }
        procesarPost(posts);
    }

    private void procesarPost(List<Post> posts) {

    }

}
