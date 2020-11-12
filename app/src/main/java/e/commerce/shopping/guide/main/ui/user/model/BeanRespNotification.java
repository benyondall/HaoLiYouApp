package e.commerce.shopping.guide.main.ui.user.model;

public class BeanRespNotification {


    public BeanRespNotification(String _content){
        setContent(_content);
    }

    private String content;

    private boolean isRead;


    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
