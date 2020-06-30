package org.blog.service;

import org.aspectj.weaver.ast.Var;
import org.blog.entiy.*;
import org.blog.mapper.Blogmapper;

import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;


@Service
public class blogserviceImpl implements blogservice  {
    @Resource(name = "blogmapper")
    Blogmapper maper = null;

    public void setMaper(Blogmapper maper) {
        this.maper = maper;
    }

    public Blogmapper getMaper() {
        return maper;
    }


    @Override
    public int queryuser(user user) {
        int queryuser = maper.queryuser(user);
        return queryuser;
    }

    @Override
    public int rigisteruser(user user) {
        //获取目录路径
        StringBuilder sb = new StringBuilder();
        sb.append("E:\\blogs\\").append(user.getUsername());
        String s = sb.toString();
        File catalog = new File(s);
        catalog.mkdirs();
        File file = new File(s+"\\sign.properties");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

         user.setSign(file.getPath());
        int i = maper.rigisteruser(user);

        //创建登录信息表
        user u =maper.querybyname(user.getUsername());
        maper.registerlogintable(u.getId());

        Integer id = maper.querybyname(user.getUsername()).getId();
        user.setId(id);
        maper.addvip(user);
        maper.createrecordtable(id);
        return i;
    }



    @Override
    public user querybyname(String username) {
        user user = maper.querybyname(username);

        return user;
    }
    List<article> qianshi =null;
    @Override
    public  List<article> queryqianshi() throws IOException {

        //获得前十标题
        List<article> qianshi = maper.queryqianshi();
        return qianshi;
    }
    public Map<String,String> ioarticle(List<article> list) throws IOException {
        //新建一个hashmap
        HashMap<String,String> hash = new HashMap<>();
        String ss =null;
        StringBuilder sb = new StringBuilder();
        for (article article : list) {
            BufferedReader reader = new BufferedReader(new FileReader(article.getMemory()));
            //拿到地址通过io取出放入string中
            if((ss=reader.readLine())!=null){
                sb.append(ss);
            }
            //加入map key为标题 ，value 为文章
            hash.put(article.getTitle(),sb.toString());
        }
        return hash;
    }

    @Override
    public vip queryvip(String username) {
        vip queryvip = maper.queryvip(username);
        return queryvip;
    }

    @Override
    public int updatevip(int grade, int sid) {
        user user = maper.queryuserbyid(sid);
        int money = user.getMoney();
        if(money>=2000){
            grade=2;
        }else if(money<1000){
            grade=1;
        }
        else  if(money>=3000){
            grade=3;
        }else  if(money>=4000){
            grade=4;
        }else  if(money>=5000){
            grade=5;
        }else  if(money>=6000){
            grade=6;
        }
        int i []={grade,money,user.getId()};
        return maper.updatevip(i);

    }

    @Override
    public int addmoney(int money,String name) {
        user user = maper.querybyname(name);
        int id = user.getId();
         money =user.getMoney()+money;
        int i [] = {money,id};
        int addmoney = maper.addmoney(i);
        //冲完钱之后更下下会员等级
//        this.updatevip(1,id);
        this.updatevip(1,id);
        return addmoney;

    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED , isolation = Isolation.DEFAULT)
    public int addaward(int award, int id,user user) {
        int i []= {award,id};

        maper.addaward(i);

        this.reducemoney(user.getMoney()-award,user.getId());

       return  0;
    }

    @Override
    public int alteruser(user user) {
       return  maper.alteruser(user);

    }

    @Override
    public List<article> querytitle(String title,int count) {
        Object [] o ={title,count};
        List<article> list = maper.querytitle(o);
        //int count =list.size()%10==0?list.size()%10:list.size()%10+1;

        return list;
    }

    @Override
    public List<article> queryalltitle(String title) {
        List<article> list = maper.queryalltitle(title);
        return list;
    }

    @Override
    public void altercount(int count,int id) {
        int arr [] = {count,id};
        maper.altercount(arr);
    }

    @Override
    public int querycount(int id,String title) {
        Object [] o={id,title};
        int i = maper.querycount(o);
        return i;
    }

    @Override
    public List<article> queryuserarticle2(int page, HttpSession s) {
        int id =(int)s.getAttribute("id");
        int arr[] = {id,page};
        return   maper.queryuserarticle2(arr);
    }


    @Override
    //自己查看自己的所有文章的list 结合checkarticle open方法
    public  List<article> queryuserarticle(HttpSession s)  {
        int id =(int)s.getAttribute("id");
        List<article> articles = maper.queryuserarticle(id);

        //Map<String, String> map = this.ioarticle(list);
        //return map;
        return articles;
    }

    @Override
    public Map<String, String> checkauserrticle(String title) throws IOException {
        return null;
    }

    @Override
    //这是所有文章标题与地址的map
    public Map<String, String> checkarticle(List<article> list) {
        Map<String,String> map = new HashMap<>();
        for (article article : list) {
            map.put(article.getTitle(),article.getMemory());
        }
        return map;
    }
    //获取一个key为文章标题，value为文章类的一个map
    public Map<String,article> articleMap(List<article> list){
        Map <String,article> map = new HashMap<>();
        for (article article : list) {
            map.put(article.getTitle(),article);
        }
        return map;

    }

    @Override
    //这就是文章！
    public String open(Map<String, String> map, String title) throws IOException {
        String s = map.get(title);
        File file = new File(s);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        StringBuilder sb = new StringBuilder();
        int length = (int)file.length();
        byte[] bytes = new byte[length];
        in.read(bytes);
        String content = new String(bytes);

        in.close();

        return content;

    }

    @Override
    public boolean deletearticle(String title,List<article> list) {
        for (article article : list) {
            if (title.equals(article.getTitle())){
                Object o [] = {article.getId(),title};
                int i = maper.deletearticle(o);
                File file = new File(article.getMemory());
                if (file.exists()){
                    return file.delete();
                }
            }
        }

//        List<article> list = maper.queryarticleaddress(o);



        return false;
    }

    @Override
    //修改文章
    /***
     * title 新标题
     * content 新内容
     * address 老文章地址
     */
    public boolean modifyarticle(HttpSession s,String content,String title,String address,String oldtitle) throws IOException {
        File file = new File(address);
        int id =(int)s.getAttribute("id");
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(address));
        out.write(content.getBytes());
        //取出旧文章标题
        String oldname = oldtitle;
        Object o []={address,title,id,oldname};

        int i = maper.updatearticleaddress(o);
        out.close();
        if(i!=0){
            return true;
        }

        return false;
    }

    @Override
    public void modifpass(String password,HttpSession s) {
        user user =(user)s.getAttribute("user");
        user.setPassword(password);
        maper.alterpass(user);
    }

    @Override
    public String querynamebyId(int id) {

        return  maper.querynamebyId(id);
    }

    @Override
    public int addrecord(String address, String name,String title,HttpSession s) {
        int id =(int)s.getAttribute("id");
        record record = new record(id,address,name,title);

        int i=0;
        List<record> records = maper.queryallrecord(id);

        if (records.isEmpty()){
            i=maper.addrecord(record);
            return  i;
        }
        for (org.blog.entiy.record record1 : records) {
            //确定同一文章不会存储两次
            if (record.equals(record1)) {
               return 0;
            }
        }
        i = maper.addrecord(record);
        return i;
    }

    @Override
    public List<record> queryrecord(int page,HttpSession s) {
        int id=(int)s.getAttribute("id");
        int arr [] = {id , page};
        List<record> records = maper.queryrecord(arr);
        return records;
    }

    @Override
    public String queryaddress(int id) {
        return maper.queryaddress(id);
    }

    @Override
    public List<record> queryallrecord(HttpSession s) {
        int id=(int)s.getAttribute("id");
        return maper.queryallrecord(id);
    }

    @Override
    public int queryaward(int id) {

        return  maper.queryaward(id);
    }

    @Override
    public int reducemoney(int money, int id) {
        int [] arr= {money,id};

        return  maper.addmoney(arr);
    }

    @Override
    public int createarticle(String title, String name,String content,HttpSession s) {
        int id=(int)s.getAttribute("id");
        user user = maper.querybyname(name);
        int amount = user.getAmount()+1;
        int [] array= {amount,id};
        maper.addamount(array);
        //获取目录路径
        StringBuilder sb = new StringBuilder();
        sb.append("E:\\blogs\\").append(name);
        File catalog = new File(sb.toString());
        //如果是第一次则创建目录
        if(!catalog.exists()){catalog.mkdirs();}
        sb.append("\\"+amount).append(".article");
        String address = sb.toString();
        //文件应该有的目录 创建文件
        File file = new File(address);
        BufferedOutputStream out=null;
        //定义一个配置文件 里面记录了用户对当前文章访问的标记
      //  File sighfile = new File(file.getParent()+"\\sigh.properties");

        try {
           // sighfile.createNewFile();
            file.createNewFile();
             out = new BufferedOutputStream(new FileOutputStream(file));
            out.write(content.getBytes());
            article article = new article();
            article.setId(id);
            article.setMemory(address);
            article.setTitle(title);
            int i= maper.createarticle(article);
            return i;
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    @Override
    public boolean querysign(String name, String title,HttpSession s) {
        user uuser =(user)s.getAttribute("user");

        File file = new File(uuser.getSign());
        Properties p = new Properties();
        FileReader reader =null;
        try {
            reader= new FileReader(file);
            p.load(reader);
            String property = p.getProperty(title);
            if (property==null){
                return true;
            }else {
                return  false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void addsign(String name, String title,HttpSession s) {
        user uuser =(user)s.getAttribute("user");
        String sign = uuser.getSign();
        Properties p = new Properties();
        p.setProperty(title,name);
        try {
            FileWriter writer = new FileWriter(new File(sign),true);
            p.store(writer,"sign");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int modiflogin(int status, int id) {
        int [] arr ={status , id};
        int i = maper.modiflogin(arr);
        return i;
    }

    @Override
    public login querylogin(int id) {
        return  maper.querylogin(id);
    }

    @Override
    public void clearLogin() {
        maper.clearLogin();
    }
//    @Test
//    public void test(){
////        String s="D:\\我的一\\个道姑朋友.mp3";
////        int i = s.lastIndexOf("\\");
////        String substring = s.substring(i+1);
////        System.out.println(substring);
////        System.out.println(i);
//        File file = new File("D:\\我的一个道姑朋友.mp3");
//        System.out.println(file.getParent());
//      //  boolean b = file.renameTo(new File("D:\\hello.mp3"));
////        System.out.println(b);
//    }

}


