package com.rufeng.vuemall.domain.AO;
/*
 * Created with IntelliJ IDEA.
 * @Author: rufeng
 * @Date: 2021-11-29 19:53
 * @Version: 1.0
 * @Description:
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author rufeng
 */
public class Menu {
    private static final List<Menu> MENU_LIST = new ArrayList<>();

    static {
        Long id = 1L;
        Menu userMenu = new Menu(id++, "用户管理", "", "user-solid");
        userMenu.addSubMenu(new Menu(id++, "用户列表", "users"));
        MENU_LIST.add(userMenu);

        Menu authMenu = new Menu(id++, "权限管理", "", "coin");
        authMenu.addSubMenu(new Menu(id++, "角色管理", "roles"));
        authMenu.addSubMenu(new Menu(id++, "权限管理", "permissions"));
        MENU_LIST.add(authMenu);

        Menu goodsMenu = new Menu(id++, "商品管理", "", "goods");
        goodsMenu.addSubMenu(new Menu(id++, "商品列表", "goodsList"));
        goodsMenu.addSubMenu(new Menu(id++, "分类参数", "goodsCategoryAttr"));
        goodsMenu.addSubMenu(new Menu(id++, "商品分类", "goodsCategories"));
        MENU_LIST.add(goodsMenu);

        Menu orderMenu = new Menu(id++, "订单管理", "", "s-order");
        orderMenu.addSubMenu(new Menu(id, "订单列表", "orders"));
        MENU_LIST.add(orderMenu);
    }

    private Long id;
    private String name;
    private String path;
    private String icon;
    private List<Menu> children;

    public Menu(Long id, String name, String path, String icon) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.icon = icon;
    }

    public Menu(Long id, String name, String path) {
        this(id, name, path, "menu");
    }

    public static List<Menu> getMenuList() {
        return Collections.unmodifiableList(MENU_LIST);
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void addSubMenu(Menu subMenu) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(subMenu);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
