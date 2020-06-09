package com.example.demo.Controller;


import com.example.demo.Utils.CSVutils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/self_service/*")
public class self_service {

    private String path="C:\\pic\\";
    private String filename="";
    private List<String> res=new LinkedList<>();
    private List<String>filenames=new LinkedList<>();

    @RequestMapping(value = "upload",method ={RequestMethod.POST})
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile[] files,HttpServletRequest request){

        //判断文件夹是否存在,不存在则创建
        String user=(String) request.getSession().getAttribute("user");
        path=path+user+"\\";
        filename=path;
        System.out.println("数组的长度"+files.length);
        File file=new File(path);

        if(!file.exists()){
            file.mkdirs();
        }
        for (int i = 0; i < files.length; i++) {
            String originalFilename = files[i].getOriginalFilename();
            filenames.add(originalFilename);
            String newFileName = originalFilename;
            String newFilePath=path+newFileName; //新文件的路径
            try {
                files[i].transferTo(new File(newFilePath));//将传来的文件写入新建的文件
                System.out.println("上传图片成功进行上传文件测试");
            }catch (IllegalStateException e ) {
                //处理异常
            }catch(IOException e1){
                //处理异常
            }

            System.out.println(originalFilename);
        }
        return "success";




    }

    @RequestMapping(value = "verify")
    @ResponseBody
    public String verify(HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("user");
        System.out.println(username);
        if(username==null)return "error";
        else return "success";


        }
    @RequestMapping("/score_road")
    @ResponseBody
    public String getScore(HttpServletRequest request){

        String[] arguments = new String[] {"python", "D:\\PyCharm Community Edition 2019.1.1\\FCN_road\\FCN.py",filename};
        String out="";
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
            String line = null;

            while ((line = in.readLine()) != null) {
                System.out.println(line);
                if(line.contains("[")){
                    out=line;
                    res.add(out);
                }
            }
            in.close();
            //java代码中的process.waitFor()返回值为0表示我们调用python脚本成功，
            //返回值为1表示调用python脚本失败，这和我们通常意义上见到的0与1定义正好相反
            int re = process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
    @RequestMapping("/score_green")
    @ResponseBody
    public String getscore_green(HttpServletRequest request){

        String[] arguments = new String[] {"python", "D:\\Course\\产学研\\FCN.tensorflow-master\\FCN.tensorflow-master\\FCN.py",filename};
        String out="";
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
            String line = null;

            while ((line = in.readLine()) != null) {
                System.out.println(line);
                if(line.contains("[")){
                    out=line;
                    res.add(out);
                }
            }
            in.close();
            //java代码中的process.waitFor()返回值为0表示我们调用python脚本成功，
            //返回值为1表示调用python脚本失败，这和我们通常意义上见到的0与1定义正好相反
            int re = process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return out;
    }

    @RequestMapping(value="delete",method={RequestMethod.POST})
    @ResponseBody
    public String delete_pic(String[] filenames,HttpServletRequest request){
        String user=(String) request.getSession().getAttribute("user");
        String path = "C:\\pic\\"+user+"\\";		//要遍历的路径
       for(String name:filenames){
           File file = new File(path+name);
           if (file.isFile() && file.exists()) {
               file.delete();
           }
       }

        return "success";
    }
    @RequestMapping(value="get_pic",method={RequestMethod.GET})
    @ResponseBody
    public List<String> get_pic(HttpServletRequest request){
        String user=(String) request.getSession().getAttribute("user");
        String path = "C:\\pic\\"+user;		//要遍历的路径
        File file = new File(path);		//获取其file对象
        File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
        List<String>files=new LinkedList<>();
        if(fs==null)return null;
        for(File f:fs){					//遍历File[]数组
            String name=f.getName();
            files.add(name);
        }
        System.out.println(files);
        return files;
    }
    @RequestMapping(value="download", method=RequestMethod.GET)
    public ResponseEntity<byte[]> csv() throws Exception {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("pic_name", "Comfort", "Beauty");
        File tempFile = File.createTempFile("vehicle", ".csv");
        FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
        CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(fileOutputStream), csvFormat);
        int num=res.size();
        List<String[]>scs=new LinkedList<>();
        for(int i=0;i<num;i++){
            String scores=res.get(i);
            scores= scores.replaceAll("\\[","");
            scores= scores.replaceAll("\\]","");
            String []s=scores.split(",");
            scs.add(s);
        }
        num=filenames.size();
        for(int i=0;i<num;i++){

            csvPrinter.printRecord(filenames.get(i), scs.get(0)[i], scs.get(1)[i]);
        }

        csvPrinter.flush();
        csvPrinter.close();
        fileOutputStream.close();
        FileInputStream fis = new FileInputStream(tempFile);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int n;
        while ((n = fis.read(b)) != -1)
        {
            bos.write(b, 0, n);
        }
        fis.close();
        bos.close();
        HttpHeaders httpHeaders = new HttpHeaders();
        String fileName = new String("评分结果.csv".getBytes("UTF-8"), "iso-8859-1");
        httpHeaders.setContentDispositionFormData("attachment", fileName);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ResponseEntity<byte[]> filebyte = new ResponseEntity<byte[]>(bos.toByteArray(), httpHeaders, HttpStatus.CREATED);
        return filebyte;
    }



}
