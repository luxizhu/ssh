package util;

import entity.AlbumForm;
import sun.reflect.annotation.ExceptionProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caopeihe on 2016-9-7.
 */
public class MyPagination {
    public List<AlbumForm> list = null;
    private int recordCount = 0;
    private int pagesize = 0;
    private int maxPage = 0;

    /**
     * 初始化页面信息
      * @param list   保存查询结果
     * @param page   指定当前页面
     * @param pagesize   每页显示记录数
     * @return   要显示记录的list对象
     */
    public List<AlbumForm> getInitPage(List<AlbumForm> list, int page, int pagesize){
        List<AlbumForm> newList = new ArrayList<AlbumForm>();
        this.list = list;
        recordCount = list.size();
        this.pagesize = pagesize;
        this.maxPage = getMaxPage();
        try{
            for(int i = (page - 1)*pagesize;i<=page*pagesize - 1; i++){
                try{
                    if(i>=recordCount){
                        break;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                newList.add((AlbumForm) list.get(i));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return newList;
    }

    /**
     * 获取指定页面数据
     * @param page   指定当前页数
     * @return   要显示记录的list对象
     */
    public List<AlbumForm> getAppointPage(int page){
        List<AlbumForm> newList = new ArrayList<AlbumForm>();
        try{
            for(int i = (page - 1)*pagesize; i<=page*pagesize-1;i++){
                try{
                    if(i>=recordCount){
                        break;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                newList.add((AlbumForm)list.get(i));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return newList;
    }

    /**
     * 获取当前页数
     * @param str   指定页面页数
     * @return   处理后的页数
     */
    public int getPage(String str){
        if(str == null){
            str="0";
        }
        int page = Integer.parseInt(str);
        if(page < 1){
            page=1;
        }else{
            if(((page - 1)*pagesize)>recordCount){
                page=maxPage;
            }
        }
        return page;
    }

    /**
     * 输出记录导航
     * @param page   当前页数
     * @param url   URL地址
     * @param para   要传递的参数
     * @return
     */
    public String printCtrl(int page, String url, String para){
        String strHtml = "<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr>" +
                "<td height='24' align='right'>当前页数:[" + page + "/" + maxPage +"]&nbsp;";
        try{
            if(page>1){
                strHtml = strHtml + "<a href ='" + url +"&page=1" +para+"'>第一页</a>";
                strHtml = strHtml + "<a href='" + url +"&page="+(page - 1) + para + "'>上一页&nbsp;</a>";
            }
            if(page < maxPage){
                strHtml = strHtml + "<a href='"+url+"&page="+(page + 1) + para + "'>下一页</a>" +
                        "<a href='" + url + "&page="+maxPage + para +"'>最后一页&nbsp;</a>";
            }
            strHtml = strHtml + "</td></tr></table>";
        }catch(Exception e){
            e.printStackTrace();
        }
        return strHtml;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
}
