package com.nsi.okexsay.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import com.nsi.okexsay.ui.base.BaseActivity;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/** 
 * 文件操作工具包
 * @author zhangyuran 2014/10/25
 * @version 1.0
 */
public class FileUtils {
    /**
     * 检测sdcard是否可用
     *
     * @return true为可用，否则为不可用
     */
    public static boolean sdCardIsAvailable() {
        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED))
            return false;
        return true;
    }

    /**
     * 计算SD卡的剩余空间
     * @return 返回-1，说明没有安装sd卡
     */
    public static long getFreeDiskSpace() {
        String status = Environment.getExternalStorageState();
        long freeSpace = 0;
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            try {
                File path = Environment.getExternalStorageDirectory();
                StatFs stat = new StatFs(path.getPath());
                long blockSize = stat.getBlockSize();
                long availableBlocks = stat.getAvailableBlocks();
                freeSpace = availableBlocks * blockSize / 1024;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return -1;
        }
        return (freeSpace);
    }
    /**
     * 图片压缩
     *
     * @param srcPath
     * @return
     */
    public static Bitmap getImage(String srcPath) {
        File picture = new File(srcPath);
        BitmapFactory.Options bitmapFactoryOptions = new BitmapFactory.Options();
        bitmapFactoryOptions.inJustDecodeBounds = true;
        Bitmap bmap = BitmapFactory.decodeFile(picture.getAbsolutePath(), bitmapFactoryOptions);
        bitmapFactoryOptions.inJustDecodeBounds = false;
        int be = (int) (bitmapFactoryOptions.outHeight / (float) 200);
        if (be <= 0)
            be = 1;
        bitmapFactoryOptions.inSampleSize = be;
        Bitmap bitmap = BitmapFactory.decodeFile(picture.getAbsolutePath(), bitmapFactoryOptions);
        return bitmap;
    }

    /**
     * 将指定图片写到文件中
     *
     * @param dir    文件目录
     * @param name   文件名
     * @param bitmap 文件流
     * @param size   文件质量
     */
    public static void writrePiic(String dir, String name, Bitmap bitmap, int size) {
        File file = new File(dir + File.separator + name);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, size, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据文件绝对路径获取文件名
     * @param filePath
     * @return
     */
    public static String getFileName( String filePath )
    {
        if( StringUtils.isEmpty(filePath) )	return "";
        return filePath.substring( filePath.lastIndexOf( File.separator )+1 );
    }

    /**
     * 向手机写图片
     * @param buffer
     * @param folder
     * @param fileName
     * @return
     */
    public static boolean writeFile( byte[] buffer, String folder, String fileName )
    {
        boolean writeSucc = false;

        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

        String folderPath = "";
        if( sdCardExist )
        {
            folderPath = Environment.getExternalStorageDirectory() + File.separator +  folder + File.separator;
        }
        else
        {
            writeSucc =false;
        }

        File fileDir = new File(folderPath);
        if(!fileDir.exists())
        {
            fileDir.mkdirs();
        }

        File file = new File( folderPath + fileName );
        FileOutputStream out = null;
        try
        {
            out = new FileOutputStream( file );
            out.write(buffer);
            writeSucc = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {out.close();} catch (IOException e) {e.printStackTrace();}
        }

        return writeSucc;
    }

    /**
     * 创建指定名称的文件夹
     *
     * @author zhangyuran 2014/10/25
     * @param name
     */
//    public static boolean fileMkdirs(String name) {
//
//        File dirs = new File(StaticField.SDCARD_DOWNLOAD_PATH);
//        if (!dirs.exists()) {
//            dirs.mkdirs();
//        }
//        File dir = new File(StaticField.SDCARD_DOWNLOAD_PATH + "/" + name);
//        if (!dir.exists()) {
//            boolean b = dir.mkdirs();
//            LogUtil.e("debug", "fileMkdirs " + b);
//            if (b) {
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return true;
//        }
//
//        // if (Environment.getExternalStorageState() != null) {
//        // Tools.Log("debug", "可读");
//        // return true;
//        // } else {
//        // Tools.Log("debug", "不可读");
//        // return false;
//        // }
//    }

    /**
     * 获取指定位置的文件
     *
     * @author zhangyuran 2014/10/25
     * @param name
     * @return
     */
//    public static Object getObject(String name) {
//        Object obj = null;
//        if (sdCardIsAvailable()) {
//            try {
//                File file = new File(StaticField.SDCARD_DOWNLOAD_PATH + "/"
//                        + name);
//                if (file.exists()) {
//                    FileInputStream fis = new FileInputStream(file);
//                    ObjectInputStream ois = new ObjectInputStream(fis);
//                    obj = ois.readObject();
//                    ois.close();
//                    fis.close();
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//        return obj;
//    }

    /**
     * 保存文件到指定位置
     *
     * @author zhangyuran 2014/10/25
     * @param obj
     * @param name
     */
//    public static void saveObject(Object obj, String name) {
//        if (sdCardIsAvailable()) {
//            try {
//                File file = new File(StaticField.SDCARD_DOWNLOAD_PATH + "/"
//                        + name);
//                if (!file.getParentFile().exists()) {
//                    file.getParentFile().mkdirs();
//                }
//                if (!file.exists()) {
//                    file.createNewFile();
//                }
//                FileOutputStream fos = new FileOutputStream(
//                        StaticField.SDCARD_DOWNLOAD_PATH + "/" + name);
//                ObjectOutputStream oos = new ObjectOutputStream(fos);
//                oos.writeObject(obj);
//                oos.close();
//                fos.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * 保存数据
     *
     * @author zhangyuran 2014/10/25
     * @param obj
     * @param name
     */
//    public static void saveFile(String obj, String name) {
//        if (sdCardIsAvailable()) {
//            try {
//                File dirfile = new File(StaticField.SDCARD_DOWNLOAD_PATH
//                        + "/data/");
//                if (!dirfile.exists()) {
//                    dirfile.mkdir();
//                }
//                String path = StaticField.SDCARD_DOWNLOAD_PATH + "/data/"
//                        + name + "-" + System.nanoTime() + ".txt";
//                File file = new File(path);
//                if (!file.exists()) {
//                    file.createNewFile();
//                }
//                FileOutputStream fos = new FileOutputStream(path);
//                fos.write(obj.getBytes());
//                fos.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * 删除指定位置的文件
     *
     * @author zhangyuran 2014/10/25
     * @return
     */
//    public static void DelObject(String name) {
//        if (sdCardIsAvailable()) {
//            try {
//                File file = new File(StaticField.SDCARD_DOWNLOAD_PATH + "/"
//                        + name);
//                if (file.exists()) {
//                    file.delete();
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static long getFileSizes(File f) throws Exception {
        long s = 0;
        if (sdCardIsAvailable()) {
            if (f.exists()) {
                FileInputStream fis = null;
                fis = new FileInputStream(f);
                s = fis.available();
            } else {
                f.createNewFile();
                // System.out.println("文件不存在");
            }
        }
        return s;
    }

    public static long getFileSize(File f) throws Exception {
        long size = 0;
        if (sdCardIsAvailable()) {
            File flist[] = f.listFiles();
            for (int i = 0; i < flist.length; i++) {
                if (flist[i].isDirectory()) {
                    size = size + getFileSize(flist[i]);
                } else {
                    size = size + flist[i].length();
                }
            }
        }
        return size;
    }


    /**
     *  转换文件大小
     * B,K,M,G
     * **/
    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("0.0");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 递归求取目录文件个数
     * **/
    public static long getlist(File f) {
        long size = 0;
        File flist[] = f.listFiles();
        size = flist.length;
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getlist(flist[i]);
                size--;
            }
        }
        return size;
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param deleteThisPath

     * @return
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath)
            throws IOException {
        if (sdCardIsAvailable()) {
            if (!TextUtils.isEmpty(filePath)) {
                File file = new File(filePath);

                if (file.isDirectory()) {// 处理目录
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            }
        }
    }

    /**
     * 删除文件
     * @param fileName
     * @return
     */
    public static boolean deleteFile(String fileName) {
        boolean status;
        SecurityManager checker = new SecurityManager();

        if (!fileName.equals("")) {

            File path = Environment.getExternalStorageDirectory();
            File newPath = new File(path.toString() + fileName);
            checker.checkDelete(newPath.toString());
            if (newPath.isFile()) {
                try {
                    LogUtil.i("DirectoryManager deleteFile", fileName);
                    newPath.delete();
                    status = true;
                } catch (SecurityException se) {
                    se.printStackTrace();
                    status = false;
                }
            } else
                status = false;
        } else
            status = false;
        return status;
    }
    
    //根据uri获取文件路径
    public static String getPath(Context context, Uri uri) {

        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection,null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        }

        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }
    /**
     * 删除某目录下文件
     *
     * @return
     */
    public static void deleteAllFiles(File root) {  
        File files[] = root.listFiles();  
        if (files != null)  
            for (File f : files) {  
                if (f.isDirectory()) { // 判断是否为文件夹  
                    deleteAllFiles(f);  
                    try {  
                        f.delete();  
                    } catch (Exception e) {  
                    }  
                } else {  
                    if (f.exists()) { // 判断是否存在  
                        deleteAllFiles(f);  
                        try {  
                            f.delete();  
                        } catch (Exception e) {  
                        }  
                    }  
                }  
            }  
    } 
    /**
	 * 读取表情配置文件
	 * 
	 * @param context
	 * @return
	 */
	public static List<String> getEmojiFile(Context context) {
		try {
			List<String> list = new ArrayList<String>();
			InputStream in = context.getResources().getAssets().open("emoji");
			BufferedReader br = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));
			String str = null;
			while ((str = br.readLine()) != null) {
				list.add(str);
			}

			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


    /**
     * 获取拍照暂时存储位置
     *
     * @param currentTime
     * @return
     */
    public static Uri getSDImageUri(BaseActivity activity, String currentTime) {
        final String CACHE_IMG = SDCardUtils.getSdcardPath() + "/integral";
        File imageDirectory = new File(CACHE_IMG);
        if (!imageDirectory.exists()) {
            imageDirectory.mkdirs();
        }
        File file = new File(CACHE_IMG, currentTime + ".jpg");
        if (android.os.Build.VERSION.SDK_INT < 24) {
            return Uri.fromFile(file);
        } else {
            Uri imageUri = FileProvider.getUriForFile(activity, "com.nsi.okexsay.fileprovider", file);
            return imageUri;
        }
    }


}