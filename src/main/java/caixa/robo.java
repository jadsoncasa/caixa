package caixa;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class robo {

	public static WebDriver driver;
	public static String texto = null;
	public static File arquivoCSV = new File("dados\\robo.csv");
	public static int i=1;
	
	public static void main(String[] args) {
try {
			
			/*ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			System.setProperty("webdriver.chrome.driver","driver\\chromedriver.exe");
			driver = new ChromeDriver(options);*/
			
			

			System.setProperty("webdriver.gecko.driver","driver\\geckodriver.exe");	
			//driver.navigate().refresh();
			driver = new FirefoxDriver();			
			driver.get("https://habitacao.caixa.gov.br/siopiweb-web/");
			Thread.sleep(2000);
			driver.manage().window().maximize();
			
		    driver.findElement(By.id("username")).clear();
		    driver.findElement(By.id("username")).sendKeys("filial02@famaempresarial.com");		
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys("7gVvfMXT");
		    driver.findElement(By.linkText("Entrar")).click();
		    
			//driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "n");
	        
		    Thread.sleep(2000);
		    
		   /* for(String winHandle:driver.getWindowHandles()) {
		    	  driver.switchTo().window(winHandle);
		    }
	        
		    driver.navigate().to("https://habitacao.caixa.gov.br/siopiweb-web/siopientrada.do");*/
		    //driver.switchTo().frame("corpoHome");		 
		    
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			
try {
	Scanner leitor = new Scanner(arquivoCSV);
    String linhasDoArquivo = new String();
    leitor.nextLine();
    driver.switchTo().frame("corpoHome");	

	while(leitor.hasNext()){
	
	try {
	    
	  	
	  	//driver.switchTo().frame("corpoHome");
		
	    linhasDoArquivo = leitor.nextLine();
        String[] valoresEntreVirgulas = linhasDoArquivo.split(",");
		
        driver.findElement(By.id("cpfCnpj")).clear();
        driver.findElement(By.id("cpfCnpj")).click();
        driver.findElement(By.id("cpfCnpj")).sendKeys(valoresEntreVirgulas[1]);
        driver.findElement(By.xpath("//*[@id=\"acoes_nav_superior\"]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"formulario\"]/table/tbody/tr/td[1]/a")).click();
	    Thread.sleep(3000);			    
	    driver.findElement(By.xpath("//*[@id=\"Reserva\"]")).click();
	    Thread.sleep(3000);
	    try {
	    	texto = driver.findElement(By.xpath("//*[@id=\"formulario\"]/table[4]/tbody/tr[2]/td[2]/span")).getText();						    	
	    }catch (Exception e) {
	    	try {
	    		texto = driver.findElement(By.xpath("//*[@id=\"formulario\"]/table[3]/tbody/tr[2]/td[2]/span")).getText();
			} catch (Exception e2) {
				texto = driver.findElement(By.xpath("//*[@id=\"formulario\"]/table[2]/tbody/tr[2]/td[4]")).getText();
				/*try {
					texto = driver.findElement(By.xpath("//*[@id=\"formulario\"]/table[2]/tbody/tr[2]/td[4]")).getText();
				} catch (Exception e3) {
					// TODO: handle exception
				}*/
			}
	    	
		}					 
	    if(texto.equals("Cancelada")||texto.equals("Falhou")) {
			        Thread.sleep(300);
			        driver.findElement(By.xpath("//*[@id=\"formulario\"]/table[1]/thead/tr/th[2]/img")).click();
			        Thread.sleep(300);
			        driver.findElement(By.xpath("//*[@id=\"AlterarP\"]")).click();
			        Thread.sleep(300);
			        driver.findElement(By.xpath("//*[@id=\"acoes_nav_inferior\"]/a[1]")).click();
			        Thread.sleep(300);
			        driver.findElement(By.xpath("//*[@id=\"acoes_nav_inferior\"]/a")).click();
			        Thread.sleep(300);
			        driver.findElement(By.id("valOperacaoPI")).click();
			        Thread.sleep(300);
			        driver.findElement(By.xpath("//*[@id=\"acoes_nav_superior\"]/a[1]")).click();
			        Thread.sleep(300);
			        driver.findElement(By.xpath("//*[@id=\"formulario\"]/table[4]/tbody/tr[1]/td[2]/input")).clear();
			        driver.findElement(By.xpath("//*[@id=\"formulario\"]/table[4]/tbody/tr[1]/td[2]/input")).sendKeys(valoresEntreVirgulas[2]);
			        Thread.sleep(300);
			        driver.findElement(By.xpath("//*[@id=\"acoes_nav_superior\"]/a[1]")).click();
			        Thread.sleep(300);
			        driver.findElement(By.xpath("//*[@id=\"acoes_nav_inferior\"]/a[1]")).click();
			  }     i++;
	  }catch (Exception e) {
		  	System.out.println(i);
		  	driver.navigate().refresh();
		  	driver.switchTo().frame("corpoHome");
		  	continue;
		  		
	}
	  
	  driver.navigate().refresh();
	  driver.switchTo().frame("corpoHome");
	  Thread.sleep(4000);
	  //driver.navigate().to("https://habitacao.caixa.gov.br/siopiweb-web/siopientrada.do");
}
	driver.close();
} catch (Exception e) {
System.out.println(e.getMessage());
driver.close();
}



	}
	

}
