/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalassignment;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

class similar
{
    public ArrayList <String> cryptoList=new ArrayList<>();
    public ArrayList <String> cryptoName=new ArrayList<>();
    public ArrayList <Double> cryptoPrice=new ArrayList<>();
    public similar()
    {
        
    }
    
    public ArrayList<String> getName()
    {
        return cryptoName;
    }
    public ArrayList<Double> getPrice()
    {
        return cryptoPrice;
    }
    
    public ArrayList<String> getData()
    {
        final String url="https://markets.businessinsider.com/cryptocurrencies";
        
        try
        {
            final Document document=Jsoup.connect(url).get();
            for(Element row : document.select("table.table.table--col-1-font-color-black.table--suppresses-line-breaks.table--fixed tr"))
            {
                if(row.select("td.table__td:nth-of-type(1)").text().equals(""))
                {
                }
                else
                {
                    String name=row.select("td.table__td:nth-of-type(1)").text();
                    String price=row.select("td.table__td:nth-of-type(2)").text();
                    String stringPrice=price.replace(",","");
                    double price2=Double.parseDouble(stringPrice);
                    this.cryptoName.add(name);
                    this.cryptoPrice.add(price2);
                    this.cryptoList.add(name+"      "+stringPrice);
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println();
        }
        return cryptoList;
    }
}

class admin extends similar
{
    public ArrayList <String> cryptoListAdmin=super.getData();
    public ArrayList <String> cryptoNameAdmin=super.getName();
     
    
    public admin()
    {
      
    }
    public void adminPage()
    {
        GridPane gp=new GridPane();
        Text text=new Text("Welcome to admin page");
        text.setFont(Font.font("Freestyle Script",FontWeight.BOLD,70));
        text.setFill(Color.ORANGE);
        Text text3=new Text("I want to:");
        text3.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        gp.add(text,0,0);
        gp.add(text3,0,1);
        
        Button btDefine=new Button("Define crypto");
        gp.add(btDefine,0,2);
        
        admin a1=new admin();
        btDefine.setOnAction((ActionEvent e) -> {
            a1.define();
        });
        
        Stage s2=new Stage();
        Scene scene3=new Scene(gp,420,300);
        s2.setScene(scene3);
        s2.setTitle("Admin Page");
        s2.show();
    }
    public void define()
    {
        GridPane gp=new GridPane();
        Text name1=new Text("Crypto Name    Crypto Price(US$) ");
        Text price1=new Text("   ");
        Text name2=new Text("Crypto Name    Crypto Price(US$) ");
        Text price2=new Text("   ");
        Text name3=new Text("Crypto Name     Crypto Price(US$)");
        Text price3=new Text("   ");
        Text name4=new Text("Crypto Name     Crypto Price(US$)");
        Text price4=new Text("   ");
        name1.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        price1.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        name2.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        price2.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        name3.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        price3.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        name4.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        price4.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        Button btSortAsc=new Button("Sort Ascending Order");
        Button btSortDes=new Button("Sort Descending order");
        gp.add(name1,0,0);
        gp.add(price1,1,0);
        gp.add(name2,2,0);
        gp.add(price2,3,0);
        gp.add(name3,4,0);
        gp.add(price3,5,0);
        gp.add(name4,6,0);
        gp.add(price4,7,0);
        gp.add(btSortAsc,8,0);
        gp.add(btSortDes,8,1);
        
        for(int i=0;i<100;i++)
        { 
            TextField tempName=new TextField(cryptoListAdmin.get(i));
            tempName.setEditable(false);
            if(i>0 && i<25)
            {
                gp.add(tempName,0,i);
                
            }
            if(i>25 && i<50)
            {
                gp.add(tempName,2,i-25);
                
            }
            if(i>50 && i<75)
            {
                gp.add(tempName,4,i-50);
            }
            if(i>75 && i<100)
            {
                gp.add(tempName,6,i-75);
            }
        }
         
        btSortAsc.setOnAction((ActionEvent e) -> {
            Collections.sort(cryptoListAdmin);
           
             for(int i=0;i<100;i++)
             { 
                 TextField tempName=new TextField(cryptoListAdmin.get(i));
                 tempName.setEditable(false);
                 if(i>0 && i<25)
                {
                    gp.add(tempName,0,i);

                }
                if(i>25 && i<50)
                {
                    gp.add(tempName,2,i-25);

                }
                if(i>50 && i<75)
                {
                    gp.add(tempName,4,i-50);
                }
                if(i>75 && i<100)
                {
                    gp.add(tempName,6,i-75);
                }
            }
        });
        btSortDes.setOnAction((ActionEvent e) -> {
            Collections.sort(cryptoListAdmin,Collections.reverseOrder());
            
               for(int i=0;i<100;i++)
               { 
                 TextField tempName=new TextField(cryptoListAdmin.get(i));
                 tempName.setEditable(false);
                 if(i>0 && i<25)
                {
                    gp.add(tempName,0,i);

                }
                if(i>25 && i<50)
                {
                    gp.add(tempName,2,i-25);

                }
                if(i>50 && i<75)
                {
                    gp.add(tempName,4,i-50);
                }
                if(i>75 && i<100)
                {
                    gp.add(tempName,6,i-75);
                }
            }
        });
        Text text1=new Text("I want to define:(Crypto Name)");
        text1.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        TextField tfdefineCryptoName=new TextField();
        tfdefineCryptoName.setMinWidth(10);
        tfdefineCryptoName.setEditable(true);
        Button btOK=new Button("OK");
        gp.add(text1,8,2);
        gp.add(tfdefineCryptoName,8,3);
        gp.add(btOK,8,4);
        GridPane.setHalignment(btOK, HPos.RIGHT);
        
        btOK.setOnAction((ActionEvent e)-> {
        if(cryptoNameAdmin.contains(tfdefineCryptoName.getText()))
                {
                    TextArea des=new TextArea();
                    gp.add(des,8,6);
                    des.setEditable(true);
                    des.setMaxHeight(15);
                    Button btSave=new Button("Save");
                    gp.add(btSave,8,7);
                    GridPane.setHalignment(btSave, HPos.RIGHT);
                    try(ObjectInputStream input=new ObjectInputStream(new FileInputStream("defineFile.txt"));)
                    {
                        String[] newdefineArray=(String[])(input.readObject());
                        des.setText(newdefineArray[cryptoNameAdmin.indexOf(tfdefineCryptoName.getText())]);
                        GridPane.setHalignment(btSave, HPos.RIGHT);
                    }
                    catch(Exception ex)
                    {
                        System.out.println();
                    }
                    
                    btSave.setOnAction((ActionEvent e2) -> {   
                        try(ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream("defineFile.txt"));)
                    {
                        String []defineArray=new String[100];
                        defineArray[cryptoNameAdmin.indexOf(tfdefineCryptoName.getText())]=des.getText();
                        output.writeObject(defineArray);
                    }
                    catch(Exception ex)
                    {
                        System.out.println();
                    }
                    });
                }
                else
                {
                    Text reject=new Text("Crypto input not availble");
                    gp.add(reject,8,5);
                }
            
        });
        
        Stage s1=new Stage();
        Scene scene2=new Scene(gp,1500,1000);
        s1.setScene(scene2);
        s1.setTitle("Define");
        s1.show();
    }
}



class customer extends similar
{
   
    public ArrayList <String> cryptoNameCustomer=super.getName();
    public ArrayList <Double> cryptoPriceCustomer=super.getPrice();
    public ArrayList<String> cryptoName=new ArrayList<>();
    public ArrayList<Integer> cryptoAmount=new ArrayList<>();
    public ArrayList<String> cryptoDate=new ArrayList<>();
    public ArrayList<Double> cryptoTotal=new ArrayList<>();
    public double topup=0.0;
    
    
    public customer() 
    {
        if(new File("topupFile.txt").exists())
        {
            try(RandomAccessFile inout=new RandomAccessFile("topupFile.txt","rw"))
            {
                inout.seek(0);
                this.topup=inout.readDouble();
            }
            catch(Exception ex)
            {
                System.out.println();
            }
        }
    }
    public void customerPage() 
    {
        GridPane gp2=new GridPane();
        Text text=new Text("Welcome to customer page");
        text.setFont(Font.font("Freestyle Script",FontWeight.BOLD,70));
        text.setFill(Color.ORANGE);
        Text text3=new Text("Press Button for Buy and sell crypto:");
        text3.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        Text text4=new Text("My portfolio:");
        text4.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        Text text5=new Text("Balance: ");
        text5.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        Text text6=new Text("US$");
        text6.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        TextField tfBalance=new TextField();
        if(new File("topupFile.txt").exists())
        {
            try(RandomAccessFile inout=new RandomAccessFile("topupFile.txt","rw"))
            {
                inout.seek(0);
                String balance=Double.toString(inout.readDouble());
                tfBalance.setText(balance);
            }
            catch(Exception ex)
            {
               System.out.println();
            }
        }
        else
        {
            tfBalance.setText("0.00");
        }
        
        tfBalance.setEditable(false);
        tfBalance.setAlignment(Pos.CENTER_LEFT);
        Button btTopUp=new Button("Topup");
        Button btRefresh=new Button("Refresh");
        Button btBuyAndSell=new Button("Buy/Sell crypto");
        Button btPortfolio=new Button("Portfolio");
        
        gp2.add(text,0,0);
        gp2.add(text5,0,1);
        gp2.add(text6,1,1);
        gp2.add(tfBalance,2,1);
        gp2.add(btRefresh,3,1);
        gp2.add(btTopUp,0,2);
        gp2.add(text3,0,3);
        gp2.add(btBuyAndSell,0,4);
        gp2.add(text4,0,5);
        gp2.add(btPortfolio,0,6);
        btRefresh.setOnAction((ActionEvent e) -> {
            if(new File("topupFile.txt").exists())
            {
                try(RandomAccessFile inout=new RandomAccessFile("topupFile.txt","rw"))
                {
                    inout.seek(0);
                    String balance=Double.toString(inout.readDouble());
                    tfBalance.setText(balance);
                }
                catch(Exception ex)
                {
                    System.out.println();
                }
                
            }
            else
            {
                tfBalance.setText("0.00");
            }
            
        });
        btTopUp.setOnAction((ActionEvent e) -> {
            topupProcess();
        });
        btBuyAndSell.setOnAction((ActionEvent e) -> {
            getData();
            buyAndSellProcess();
        });
        btPortfolio.setOnAction((ActionEvent e) -> {
                 portfolioPage();
        });

        Stage s1=new Stage();
        Scene scene2=new Scene(gp2,800,300);
        s1.setScene(scene2);
        s1.setTitle("Customer Page");
        s1.show();
    }
    public void portfolioPage()
    {
        GridPane gp=new GridPane();
        gp.setHgap(46);
        gp.setVgap(10);
        Text text1=new Text("Crypto name");
        Text text2=new Text("Crypto amount");
        Text text3=new Text("Crypto Purchase Date");
        Text text4=new Text("Crypto Purchase Price(US$)");
        Text text5=new Text("Crypto Current Price(US$)");
        Text text6=new Text("Interest");
        Text text7=new Text("Total benefit/loss");
        gp.add(text1,0,0);
        gp.add(text2,1,0);
        gp.add(text3,2,0);
        gp.add(text4,3,0);
        gp.add(text5,4,0);
        gp.add(text6,5,0);
        gp.add(text7,6,0);
        text1.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        text2.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        text3.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        text4.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        text5.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        text6.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        text7.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        
         try (RandomAccessFile output=new RandomAccessFile("newFile.txt","rw"))
         {
             output.seek(0);
             
             for(int i=0;i<output.length()*1000;i++)
             {
                 String name=output.readUTF();
                 Text nameText=new Text(name);
                 gp.add(nameText,0,i+1);
                 
                 int amount=output.readInt();
                 Text amountText=new Text(Integer.toString(amount));
                 gp.add(amountText,1,i+1);
                 
                 gp.add(new Text(output.readUTF()),2,i+1);
                 
                 double price=output.readDouble();
                 Text priceText=new Text(Double.toString(price));
                 gp.add(priceText,3,i+1);
                 
                 double currentPrice=cryptoPriceCustomer.get(cryptoNameCustomer.indexOf(name));
                 double interest=(((amount*currentPrice)-price)/price)*100;
                 gp.add(new Text(Double.toString(currentPrice*amount)),4,i+1);
                 gp.add(new Text(Double.toString(interest)),5,i+1);
                 gp.add(new Text(Double.toString((amount*currentPrice)-price)),6,i+1);
             }
         }
         catch(Exception ex)
         {
              Text nothing=new Text("");
              gp.add(nothing,1,50);
         }
        
        Stage s1=new Stage();
        Scene scene2=new Scene(gp,1400,500);
        s1.setScene(scene2);
        s1.setTitle("Portfolio Page");
        s1.show();
    }
    public void buyAndSellProcess()
    {
        GridPane gp=new GridPane();
        Text name1=new Text("Crypto Name    Crypto Price(US$) ");
        Text price1=new Text("   ");
        Text name2=new Text("Crypto Name    Crypto Price(US$) ");
        Text price2=new Text("   ");
        Text name3=new Text("Crypto Name     Crypto Price(US$)");
        Text price3=new Text("   ");
        name1.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        price1.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        name2.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        price2.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        name3.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        price3.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        Button btSortAsc=new Button("Sort Ascending Order");
        Button btSortDes=new Button("Sort Descending order");
        gp.add(name1,0,0);
        gp.add(price1,1,0);
        gp.add(name2,2,0);
        gp.add(price2,3,0);
        gp.add(name3,4,0);
        gp.add(price3,5,0);
        gp.add(btSortAsc,6,0);
        gp.add(btSortDes,6,1);
        
        for(int i=0;i<100;i++)
        {
            TextField tempName=new TextField(cryptoList.get(i));
            tempName.setEditable(false);
            
            if(i>0 && i<33)
            {
                gp.add(tempName,0,i);
                
            }
            if(i>33 && i<66)
            {
                gp.add(tempName,2,i-33);
                
            }
            if(i>66 && i<100)
            {
                gp.add(tempName,4,i-66);
            }
        }
        
        btSortAsc.setOnAction((ActionEvent e) -> {
            Collections.sort(cryptoList);
            for(int i=0;i<100;i++)
             { 
                 TextField tempName=new TextField(cryptoList.get(i));
                 tempName.setEditable(false);
                 if(i>0 && i<33)
                {
                    gp.add(tempName,0,i);
                }
                if(i>33 && i<66)
                {
                    gp.add(tempName,2,i-33);
                }
                if(i>66 && i<100)
                {
                    gp.add(tempName,4,i-66);
                }
            }
        });
        btSortDes.setOnAction((ActionEvent e) -> {
            Collections.sort(cryptoList,Collections.reverseOrder());
            
               for(int i=0;i<100;i++)
               {
                   TextField tempName=new TextField(cryptoList.get(i));
                   tempName.setEditable(false);
                   if(i>0 && i<33)
                    {
                        gp.add(tempName,0,i);
                    }
                    if(i>33 && i<66)
                    {
                        gp.add(tempName,2,i-33);
                    }
                    if(i>66 && i<100)
                    {
                        gp.add(tempName,4,i-66);
                    }
               }
        });
        Button btWantBuy=new Button("I want to Buy crypto");
        Button btWantSell=new Button("I want to Sell crypto");
        gp.add(btWantBuy,6,3);
        gp.add(btWantSell,6,4);
        btWantBuy.setOnAction((ActionEvent e) -> {
        Text Hint1=new Text("Hint:Press Enter for next step");
        gp.add(Hint1,6,9);
        Text buyText=new Text("I want to buy(Crypto Name)");
        TextField tfBuy=new TextField();
        gp.add(buyText,6,10);
        gp.add(tfBuy,6,11);
        tfBuy.setEditable(true);
        
        tfBuy.setOnAction((ActionEvent e1) -> {
            if(cryptoNameCustomer.contains(tfBuy.getText()))
            {
                Text X1=new Text("Buy Amount:");
                TextField textSuccess1=new TextField("Transaction Successful");
                TextField textInsufficient=new TextField("Insufficient Balance");
                TextField tfBuyAmount=new TextField();
                tfBuyAmount.setEditable(true); 
                gp.add(X1,6,13);
                gp.add(tfBuyAmount,6,14);
                    
                tfBuyAmount.setOnAction((ActionEvent e2) -> {
                    Text totalText1=new Text("Total (US$)");
                try{
                    int totalInt=Integer.parseInt(tfBuyAmount.getText());
                    double totalBuy=cryptoPriceCustomer.get(cryptoNameCustomer.indexOf(tfBuy.getText()))*totalInt;
                    String totalString=Double.toString(totalBuy);
                    TextField tfTotalBuy=new TextField(totalString);
                    tfTotalBuy.setEditable(false);
                    Button btBuy=new Button("Comfirm");
                    gp.add(totalText1,6,15);
                    gp.add(tfTotalBuy,6,16);
                    gp.add(btBuy,6,17);
                    GridPane.setHalignment(btBuy, HPos.RIGHT);
                    btBuy.setOnAction((ActionEvent e3) -> {
                        if(topup>=totalBuy)
                        {
                            gp.add(textSuccess1,6,18);
                            
                            try(RandomAccessFile inout=new RandomAccessFile("topupFile.txt","rw"))
                                {
                                   
                                   inout.setLength(0);
                                   inout.writeDouble(this.topup-=totalBuy);
                                }
                                catch(Exception ex)
                                {
                                    System.out.println();
                                }
                            
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                            LocalDateTime now = LocalDateTime.now();  
                            this.cryptoName.add(tfBuy.getText());
                            this.cryptoAmount.add(totalInt);
                            this.cryptoDate.add(dtf.format(now));
                            this.cryptoTotal.add(totalBuy);
                            
                            try(RandomAccessFile output=new RandomAccessFile("newFile.txt","rw"))
                            {
                                for(int i=0;i<cryptoName.size();i++)
                                 {
                                     output.writeUTF(cryptoName.get(i));
                                     output.writeInt(cryptoAmount.get(i));
                                     output.writeUTF(cryptoDate.get(i));
                                     output.writeDouble(cryptoTotal.get(i));
                                 }
                                
                            } 
                            catch (Exception ex) {
                                System.out.println();
                            }
                        }
                        else
                        {
                            gp.add(textInsufficient,6,18);
                        }
                    });
                }
                    catch(java.lang.NumberFormatException ex)
                    {
                        Text text4=new Text("Please input valid digit");
                        text4.setFont(Font.font("Calibri",FontWeight.BOLD,20));
                        text4.setFill(Color.RED);
                        gp.add(text4,7,14);
                    }
                    
                    catch(Exception ex)
                    {
                        Text text4=new Text("Please input valid digit");
                        text4.setFont(Font.font("Calibri",FontWeight.BOLD,20));
                        text4.setFill(Color.RED);
                        gp.add(text4,7,14);
                    }
                });
            }
            else
            {
                Text reject=new Text("Crypto input not availble");
                gp.add(reject,6,12);
            }
        });
        });
        
        btWantSell.setOnAction((ActionEvent e) -> {
             Text Hint2=new Text("Hint:Press Enter for next step");
             gp.add(Hint2,6,20);
             Text sellText=new Text("I want to sell(Crypto Name)");
             TextField tfSell=new TextField();
             gp.add(sellText,6,21);
             gp.add(tfSell,6,22);
             
             tfSell.setOnAction((ActionEvent e1) -> {
            
                if(this.cryptoName.contains(tfSell.getText()))
                {
                    Text X2=new Text("Sell Amount");
                    TextField textSuccess2=new TextField("Sold Successfully");
                    TextField textFail=new TextField("No enough crypto");
                    textFail.setEditable(false);
                    tfSell.setEditable(true);
                    TextField tfSellAmount=new TextField();
                    tfSellAmount.setEditable(true);
                    gp.add(X2,6,24);
                    gp.add(tfSellAmount,6,25);

                    tfSellAmount.setOnAction((ActionEvent e2) -> {
                    int num=Integer.parseInt(tfSellAmount.getText());
                    if((cryptoAmount.get(cryptoName.indexOf(tfSell.getText())))>=num)
                        {
                            int amount=cryptoAmount.get(cryptoName.indexOf(tfSell.getText()))-num;
                            double total=amount*cryptoPriceCustomer.get(cryptoNameCustomer.indexOf(tfSell.getText()));
                            Text totalText2=new Text("Total:US$");
                            TextField tfTotalSell=new TextField();
                            tfTotalSell.setEditable(false);
                            Button btComfirm2=new Button("Comfirm");
                            double num2=cryptoPriceCustomer.get(cryptoNameCustomer.indexOf(tfSell.getText()))*num;
                            tfTotalSell.setText(Double.toString(num2));
                            gp.add(totalText2,6,26);
                            gp.add(tfTotalSell,6,27);
                            gp.add(btComfirm2,6,28);
                            GridPane.setHalignment(btComfirm2, HPos.RIGHT);


                            btComfirm2.setOnAction((ActionEvent e3) -> {
                                gp.add(textSuccess2,6,29);

                                 try(RandomAccessFile inout=new RandomAccessFile("topupFile.txt","rw"))
                                 {
                                     inout.setLength(0);
                                     inout.writeDouble(this.topup+=num2);
                                 }
                                 catch(Exception ex)
                                 {
                                     System.out.println();
                                 }
                                 cryptoAmount.remove(cryptoName.indexOf(tfSell.getText()));
                                 cryptoAmount.add(cryptoName.indexOf(tfSell.getText()),amount);
                                 cryptoTotal.remove(cryptoName.indexOf(tfSell.getText()));
                                 cryptoTotal.add(cryptoName.indexOf(tfSell.getText()),total);

                                 try(RandomAccessFile output=new RandomAccessFile("newFile.txt","rw"))
                                {
                                     output.setLength(0);
                                     for(int i=0;i<cryptoName.size();i++)
                                     {
                                         output.writeUTF(cryptoName.get(i));
                                         output.writeInt(cryptoAmount.get(i));
                                         output.writeUTF(cryptoDate.get(i));
                                         output.writeDouble(cryptoTotal.get(i));
                                     }
                                } 
                                catch (Exception ex) {
                                    System.out.println("");
                                }
                            });
                        }
                    else
                        {
                            gp.add(textFail,7,25);
                        }
                    });
                }
            else
            {
                Text reject=new Text("Crypto input not availble in your bought list");
                gp.add(reject,6,23);
            }
            });
        });
        
        Stage s1=new Stage();
        Scene scene2=new Scene(gp,1300,1000);
        s1.setScene(scene2);
        s1.setTitle("Buy/Sell");
        s1.show();
    }
   
    public void topupProcess() 
    {
        
        GridPane gp=new GridPane();
        Text text1=new Text("I want topup $");
        text1.setFont(Font.font("Calibri",FontWeight.BOLD,20));
        TextField tf1=new TextField();
        tf1.setEditable(true);
        gp.add(text1,0,0);
        gp.add(tf1,1,0);
        Button btTopup=new Button("TopUp");
        gp.add(btTopup,1,1);
        GridPane.setHalignment(btTopup, HPos.RIGHT); 
        btTopup.setOnAction((ActionEvent e) -> {
            Text text2=new Text("New balance:");
            try
            {
                RandomAccessFile inout=new RandomAccessFile("topupFile.txt","rw");
                double total=Double.valueOf(tf1.getText());
               text2.setFont(Font.font("Calibri",FontWeight.BOLD,20));
                Text text3=new Text("TopUp Successful");
                text3.setFont(Font.font("Calibri",FontWeight.BOLD,20));
                this.topup += total;
                String topup2 = Double.toString(this.topup);
                TextField tf2=new TextField(topup2);
                tf2.setEditable(false);
                inout.setLength(0);
                inout.writeDouble(topup);
                gp.add(text2,0,4);
                gp.add(tf2,1,4);
                gp.add(text3,1,5);
        }
        catch(java.lang.NumberFormatException ex)
        {
            Text text4=new Text("Please input valid digit");
            text4.setFont(Font.font("Calibri",FontWeight.BOLD,20));
            text4.setFill(Color.RED);
            gp.add(text4,1,3);
        }
        catch(Exception ex)
        {
            Text text4=new Text("Your C drive is not accessible");
            text4.setFont(Font.font("Calibri",FontWeight.BOLD,20));
            text4.setFill(Color.RED);
            gp.add(text4,1,3);
        }  
        });
        
       
        

        Stage s5=new Stage();
        Scene scene5=new Scene(gp,500,200);
        s5.setScene(scene5);
        s5.setTitle("TopUp");
        s5.show();
        
    }
    
}

public class FinalAssignment extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane pane=new BorderPane();
        
        Text text=new Text("Welcome to cryptocurrency exchange application");
        text.setFont(Font.font("Freestyle Script",FontWeight.BOLD,70));
        text.setFill(Color.RED);
        Text text2=new Text("I am: ");
        text2.setFont(Font.font("Times New Roman",15));
        VBox vbox=new VBox(text,text2);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        Button btAdmin=new Button("Admin");
        Button btCustomer = new Button("Customer");
        HBox hbox=new HBox(text2,btAdmin,btCustomer);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        pane.setCenter(vbox);
        pane.setBottom(hbox);
        admin a1=new admin();
        customer c1=new customer();
        btAdmin.setOnAction(e->a1.adminPage());
        btCustomer.setOnAction(e->c1.customerPage());
        
        Scene scene =new Scene(pane,900,200);
        primaryStage.setTitle("Main Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
