package hadoop.day02;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class hadoopTest {
    @Test
    public void getFileSystem() throws URISyntaxException, IOException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.174.100:8020"), configuration);
        System.out.println(fileSystem.toString());
    }

    @Test
    public void getFileSystem2() throws URISyntaxException, IOException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://192.168.174.100:8020");
        FileSystem fileSystem = FileSystem.get(new URI("/"), configuration);
        System.out.println(fileSystem.toString());
    }

    @Test
    public void getFileSystem3() throws URISyntaxException, IOException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.newInstance(new URI("hdfs://192.168.174.100:8020"), configuration);
        System.out.println(fileSystem.toString());
    }

    @Test
    public void getFileSystem4() throws  Exception{
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://192.168.174.100:8020");
        FileSystem fileSystem = FileSystem.newInstance(configuration);
        System.out.println(fileSystem.toString());
    }

    @Test
    public void listFile() throws Exception{
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.174.100:8020"), new Configuration());
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/"));
        for (FileStatus fileStatus : fileStatuses) {
            if(fileStatus.isDirectory()){
                Path path = fileStatus.getPath();
                listAllFiles(fileSystem,path);
            }else{
                System.out.println("???????????????"+fileStatus.getPath().toString());
            }
        }
    }
    public void listAllFiles(FileSystem fileSystem,Path path) throws  Exception{
        FileStatus[] fileStatuses = fileSystem.listStatus(path);
        for (FileStatus fileStatus : fileStatuses) {
            if(fileStatus.isDirectory()){
                listAllFiles(fileSystem,fileStatus.getPath());
            }else{
                Path path1 = fileStatus.getPath();
                System.out.println("???????????????"+path1);
            }
        }
    }


    /**
     * ????????????????????????????????????
     */
    @Test
    public void queryFiles() throws Exception{
        //??????FileSystem
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.174.100:8020"), new Configuration());
        //????????????????????????
        RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator = fileSystem.listFiles(new Path("/"), true);
        //??????
        while (locatedFileStatusRemoteIterator.hasNext()){
            //?????????????????????
            LocatedFileStatus next = locatedFileStatusRemoteIterator.next();
            //??????????????????????????????
            System.out.println(next.getPath()+"----------"+next.getPath().getName());
            //????????????block??????
            BlockLocation[] blockLocations = next.getBlockLocations();
            //??????????????????block???
            System.out.println(blockLocations.length);
            //?????????????????????????????????
            for (BlockLocation blockLocation : blockLocations) {
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println(host+"-------");
                }
                        

            }
        }
        fileSystem.close();
    }

    /**
     * ???????????????
     */
    @Test
    public void mkdirFileSystem() throws Exception{
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.174.100:8020"), new Configuration());

//        fileSystem.mkdirs(new Path("/java/aaa//bb"));
        fileSystem.create(new Path("/hb.txt"));
        fileSystem.close();
    }

    /**
     * ????????????
     * @throws Exception
     */
    @Test
    public void downloadFile() throws Exception{
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.174.100:8020"), new Configuration());

        //?????????????????????
        FSDataInputStream open = fileSystem.open(new Path("/a.txt"));
        //?????????????????????
        FileOutputStream fileOutputStream=new FileOutputStream(new File("d://a.txt"));

        IOUtils.copy(open,fileOutputStream);

        IOUtils.closeQuietly(open);
        IOUtils.closeQuietly(fileOutputStream);

        fileSystem.close();
    }

    /**
     * ??????2
     * @throws Exception
     */
    @Test
    public void downloadFile2() throws Exception{
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.174.100:8020"), new Configuration());

        fileSystem.copyToLocalFile(new Path("/wordcount_out/part-r-00000"),new Path("D://b.txt"));

        fileSystem.close();
    }

    /**
     * ????????????
     */
    @Test
    public void uploadFile() throws Exception{
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.174.100:8020"), new Configuration());

        fileSystem.copyFromLocalFile(new Path("d://b.txt"),new Path("/b.txt"));

        fileSystem.close();
    }

    /**
     * ???????????????????????????hadoop
     */
    @Test
    public void  mergeFile() throws Exception{
        FileSystem root = FileSystem.get(new URI("hdfs://192.168.174.100:8020"), new Configuration(), "root");

        //??????????????????
        FSDataOutputStream fsDataOutputStream = root.create(new Path("/merge.txt"));

        //????????????????????????
        LocalFileSystem local = FileSystem.getLocal(new Configuration());

        //????????????????????????????????????
        FileStatus[] fileStatuses = local.listStatus(new Path("d://input"));

        for (FileStatus fileStatus : fileStatuses) {
            FSDataInputStream open = local.open(fileStatus.getPath());
            IOUtils.copy(open,fsDataOutputStream);
            IOUtils.closeQuietly(open);
        }

        IOUtils.closeQuietly(fsDataOutputStream);
        local.close();
        root.close();
    }

}
