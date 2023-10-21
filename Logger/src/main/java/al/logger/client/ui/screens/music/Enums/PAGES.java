package al.logger.client.ui.screens.music.Enums;

import al.logger.client.ui.bases.ComponentBase;
import lombok.Getter;
import lombok.Setter;

public enum PAGES {
    LOGIN("登录",null),
    VIEW("浏览",null),
    HOME("主页",null),
    SEARCH("搜索",null),
    PLAYLIST("歌单",null);

    @Getter
    @Setter
    private String pageName;

    @Getter
    @Setter
    private ComponentBase pageComponent;

    PAGES(String pageName, ComponentBase componentBase) {
        this.pageName = pageName;
        this.pageComponent = componentBase;
    }

}
