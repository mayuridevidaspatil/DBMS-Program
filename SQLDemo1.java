import java.sql.* ;
import java.io.*;

class SQLDemo1
{
public static void main(String[] args)
{
int choice,id;
String name,update,update_db;
PreparedStatement ps;
try
{
Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://localhost:3306/Student";
String uname="root";
String pass="student";
Connection con=DriverManager.getConnection(url,uname,pass);
InputStreamReader isr=new InputStreamReader(System.in);
BufferedReader br=new BufferedReader(isr);
do{
System.out.println("\n1.Select\t2.Insert\n3.Delete\t4.Update\n5.Exit");
System.out.println("Enter Choice ");
choice=Integer.parseInt(br.readLine());
switch(choice)
{
case 1:
String select="select * from student";
Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery(select);
while(rs.next())
{
System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getString(3));
}
break;
case 2:
System.out.println("Enter ID");
id=Integer.parseInt(br.readLine());
System.out.println("Enter name");
name=br.readLine();
System.out.println("Enter address");
String add=br.readLine();
String insert="Insert into student values(?,?,?)";
ps=con.prepareStatement(insert);
ps.setInt(1,id);
ps.setString(2,name);
ps.setString(3,add);
ps.executeUpdate();
break;
case 3:
System.out.println("Enter ID whose record you want to delete");
id=Integer.parseInt(br.readLine());
String delete="delete from student where ID=?";
ps=con.prepareStatement(delete);
ps.setInt(1,id);
ps.executeUpdate();
break;
case 4:
System.out.println("\nWhat you want to Update(name or address )");
update_db=br.readLine();if(update_db.equals("name"))
{
System.out.println("\nEnter the ID whose name is to be modified ");
id=Integer.parseInt(br.readLine());
System.out.println("\nEnter modified Name");
name=br.readLine();
update="update student set name=? where ID=?";
ps=con.prepareStatement(update);
ps.setString(1,name);
ps.setInt(2,id);
ps.executeUpdate();
}
else if(update_db.equals("ADDRESS"))
{
System.out.println("\nEnter the ID whose address is to be modified ");
id=Integer.parseInt(br.readLine());
System.out.println("\nEnter modified Address");
String adder=br.readLine();
System.out.println(adder);
update="update student set ADDRESS=? where ID=?";
ps=con.prepareStatement(update);
ps.setString(1,adder);
ps.setInt(2,id);
ps.executeUpdate();
}
break;
case 5:System.exit(0);
}
}while(choice<=5);
}
catch(Exception e)
{
System.out.println(e);
}
}
}

