package Vladimir.The.image.service.api;

import org.springframework.core.io.Resource;

public class CopyImageResponse {

    private String imageName;
    private Resource resource;


    public CopyImageResponse() {
    }

    public CopyImageResponse(String imageName, Resource resource) {
        this.imageName = imageName;
        this.resource = resource;
    }


    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Resource getResource() {
        return resource;
    }
    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
