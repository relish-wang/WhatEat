package com.xhxkj.zhcs.temp;

import java.util.ArrayList;

/**
 * Family entity.
 *
 * @author 王鑫
 */
public class FamilyBean implements java.io.Serializable {

    // Fields

    private Integer id;
    private String name;
    private ArrayList<JoinBean> joins = new ArrayList<>();
    private ArrayList<MsgBean> msgs = new ArrayList<>();

    // Constructors

    /**
     * default constructor
     */
    public FamilyBean() {
    }

    /**
     * minimal constructor
     */
    public FamilyBean(String name) {
        this.name = name;
    }

    /**
     * full constructor
     */
    public FamilyBean(String name, ArrayList<JoinBean> joins, ArrayList<MsgBean> messages) {
        this.name = name;
        this.joins = joins;
        this.msgs = messages;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<JoinBean> getJoins() {
        return this.joins;
    }

    public void setJoins(ArrayList<JoinBean> joins) {
        this.joins = joins;
    }

    public ArrayList<MsgBean> getMsgs() {
        return this.msgs;
    }

    public void setMsgs(ArrayList<MsgBean> msgs) {
        this.msgs = msgs;
    }

}