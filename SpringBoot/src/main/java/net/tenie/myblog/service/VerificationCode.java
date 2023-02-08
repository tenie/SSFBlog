package net.tenie.myblog.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random; 
import javax.imageio.ImageIO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.tenie.myblog.session.LoginSession;
import net.tenie.myblog.session.SessionUtil;  
 
  
public class VerificationCode {

	public static String getImage( HttpServletResponse response) throws IOException{
		//绘制一张图片的过程
				//1.准备一张空白的有尺寸的图片
				BufferedImage image =  
						new BufferedImage(
								220,30,
								BufferedImage.TYPE_INT_RGB);
				//2.获取图片的画笔对象
				Graphics g = image.getGraphics();
				//3.设置画笔颜色
				Random r = new Random(); 
				g.setColor(new Color(144,216,197));
				//4.绘制矩形实心的背景 
				g.fillRect(0, 0, 220, 30);
				//5.更改画笔颜色 
				//5.1设置画笔字体
				g.setFont(new Font(null,Font.BOLD,24));
				//6.绘制字符串
				String number=getNumber(5); 
				LoginSession session=SessionUtil.getSession();
				session.setImageCode(number);
				for(int i = 0; i<number.length();i++){
					g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
					g.drawString( String.valueOf(number.charAt(i)),((13*6)+(13*i)), 22); 
				}
			
				//7.设置响应流的数据格式
				response.setContentType("image/jpeg");
				//8.获取输出流
				OutputStream ops = 
					response.getOutputStream();
				//8.保存图片到输出流中
				ImageIO.write(image,"jpeg",ops);
				ops.close();
				return number;
	}
	private static String getNumber(int size){
		String cs = "ABCDEFGHIJKLMNOPQ" +"abcdefghijklnmopqrisuvwxyz"+
				"RSTUVWXYZ0123456789";
		String number="";
		Random r = new Random();
		for(int i=0;i<size;i++){
			number+=cs.charAt
			(r.nextInt(cs.length()));
		}		
		return number;
	}
	
	public static String getImage2(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//绘制一张图片的过程
				//1.准备一张空白的有尺寸的图片
				BufferedImage image = 
					new BufferedImage(
					100,30,
					BufferedImage.TYPE_INT_RGB);
				//2.获取图片的画笔对象
				Graphics g = image.getGraphics();
				//3.设置画笔颜色
				Random r = new Random();
				g.setColor(new Color(
					r.nextInt(r.nextInt(255)),
					r.nextInt(r.nextInt(255)),
					r.nextInt(r.nextInt(255))));
				//4.绘制矩形实心的背景
				g.fillRect(0, 0, 100, 30);
				//5.更改画笔颜色 
				//5.1设置画笔字体
				g.setFont(new Font(null,Font.BOLD,24));
				//6.绘制字符串
				String number=getNumber(5); 
				LoginSession session=SessionUtil.getSession();
				session.setImageCode(number);
				for(int i = 0; i<number.length();i++){
					g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
					g.drawString( String.valueOf(number.charAt(i)),(13+(13*i)), 22); 
				}
			
				//7.设置响应流的数据格式
				response.setContentType("image/jpeg");
				//8.获取输出流
				OutputStream ops = 
					response.getOutputStream();
				//8.保存图片到输出流中
				ImageIO.write(image,"jpeg",ops);
				ops.close();
				return number;
	}
}
