package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions) throws Exception{
        //add an image to the blog
        Image image = new Image();

        image.setDescription(description);
        image.setDimensions(dimensions);

        Blog blog = blogRepository2.findById(blogId).get();

        if(blog==null){
            throw new Exception("Blog is not found");
        }

        image.setBlog(blog);

        List<Image> imageList = blog.getImageList();
        imageList.add(image);
        blog.setImageList(imageList);

        blogRepository2.save(blog);
        //imageRepository2.save(image);

        return image;
    }

    public void deleteImage(Integer id){

        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        Image image = imageRepository2.findById(id).get();

        String dimension = image.getDimensions();

        int i;
        for(i=0;i<screenDimensions.length();i++){
            if(screenDimensions.charAt(i)=='X'){
                break;
            }
        }

        int l1 = Integer.valueOf(screenDimensions.substring(0, i));
        int b1 = Integer.valueOf(screenDimensions.substring(i+1));

        int totalArea = l1 * b1;

        int j;
        for (j=0;j<dimension.length();j++){
            if(dimension.charAt(j)=='X'){
                break;
            }
        }

        int l2 = Integer.valueOf(dimension.substring(0, j));
        int b2 = Integer.valueOf(dimension.substring(j+1));

        int area = l2 * b2;

        int count = (int)Math.floor(totalArea/area);

        return totalArea%area==0 ? count : count-1;
    }
}
