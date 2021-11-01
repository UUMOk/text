package PK;
import java.util.ArrayList;

import java.util.Scanner;

public interface IParking {
	public void print();
    public boolean addCar(int carType);
    public static IParams parse() throws Exception{
        IParams pr=new IParams() {
            int big = 0;
            int medium=0;
            int small=0;
            ArrayList<Integer>PlanParking=new ArrayList<>();
            public int getBig() {
                return big;
            }
            public void set(int big,int medium,int small) {
                this.big=big;
                this.medium=medium;
                this.small=small;
            }
            public int getMedium() {
                return medium;
            }

            public int getSmall() {
                return small;
            }
            public void add(int i){
                PlanParking.add(i);
            }
            public ArrayList<Integer> getPlanParking() {
                return PlanParking;
            }
        };
        int k=0,sum=0;
        Scanner sc=new Scanner(System.in);
        String init_code=sc.nextLine(),add_code,car_num,add_num,temp;
        while((k=init_code.indexOf( "addCar",k))!=-1){
            sum++;
            k+=6;
        }
        add_code=sc.nextLine();
        car_num=add_code.substring(2,add_code.indexOf("]",2));
        car_num=car_num+",";
        k=0;
        int[] num =new int[3];
        for(int i=0;i<3;i++){
            temp=car_num.substring(k,car_num.indexOf(",",k)).trim();
            k=car_num.indexOf(",",k)+1;
            num[i]=Integer.parseInt(temp);
        }
        pr.set(num[0],num[1],num[2]);

        add_num=add_code.substring(add_code.indexOf("]",2)+1,add_code.length()-1);
        k=0;int j=0;
        for(int i=0;i<add_num.length();i++){
            if(add_num.charAt(i)>='1'&&add_num.charAt(i)<='3'){
                j++;
                pr.add(add_num.charAt(i)-48);
            }
        }
        if(j!=sum){
            System.err.println("³µÁ¾Êý²»·û");
        }
        sc.close();
        return pr;
    }
}