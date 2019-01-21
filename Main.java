import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.abs;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Map<Integer, ArrayList> hashmapOne, hashmapTwo ;
        Audio one = new Audio();
        hashmapOne=one.AudioFile("C://Users//nicem//OneDrive//Рабочий стол//test//fp//1233793");
        Set<Map.Entry<Integer, ArrayList>> setOne = hashmapOne.entrySet();


        Audio two = new Audio();
        hashmapTwo=two.AudioFile("C://Users//nicem//OneDrive//Рабочий стол//test//fp//1175384");
        Set<Map.Entry<Integer, ArrayList>> setTwo = hashmapTwo.entrySet();

        ArrayList<Integer> listOne;
        ArrayList<Integer> listTwo;
        boolean check=false;
        ArrayList<Integer> ArrOne = new ArrayList<Integer>();
        ArrayList<Integer> ArrTwo = new ArrayList<Integer>();



        for (Map.Entry<Integer, ArrayList> x : setOne){
            //int min=Integer.MAX_VALUE;
            for (Map.Entry<Integer, ArrayList> y : setTwo) {
                listOne=x.getValue();
                listTwo=y.getValue();
                for (int ValueOne:listOne) {

                    for (int ValueTwo:listTwo){
                        if (ValueOne==ValueTwo){
                            System.out.println(x.getKey() + " = " + y.getKey());

                            ArrOne.add(x.getKey());
                            ArrTwo.add(y.getKey());

                            check=true;
                        }else check=false;

                        if (check==true) break;
                    }
                    if (check==true) break;
                }
            }
        }

        int n=ArrOne.size(), i=0, ctrl=0;

        int[][] Arr = new int[n][2];

        for (int m: ArrOne){
            Arr[i][0]=m;
            i++;
        }
        i=0;
        for (int m: ArrTwo){
            Arr[i][1]=m;
            i++;
        }


        //if(n>1){
        //    for(int i=1; i<n; i++){
        //        int j = i-1, t=ArrOne.get(i), tt=ArrTwo.get(i);
        //       while ((j>=0)&& (ArrOne.get(j)>t)){
        //           ArrOne.set(j+1, ArrOne.get(j));
        //            ArrTwo.set(j+1, ArrTwo.get(j));
        //            j--;
        //        }
        //        ArrOne.set(j+1, t);
        //        ArrTwo.set(j+1, tt);
        //    }
        //}

        if(i>1){
            for(i=1; i<Arr.length; i++){
                int j = i-1, t=Arr[i][0], tt=Arr[i][1];
                while ((j>=0)&&(Arr[j][0]>t)){
                    Arr[j+1][0]=Arr[j][0];
                    Arr[j+1][1]=Arr[j][1];
                    j--;
                }
                Arr[j+1][0]=t;
                Arr[j+1][1]=tt;
            }
        }
        System.out.println( "==================================" );
        System.out.println( "==================================" );
        System.out.println( "==================================" );
        System.out.println( "==================================" );
        System.out.println( "==================================" );
        System.out.println( "==================================" );

        for ( i = 0; i<n; i++){
            System.out.println(Arr[i][0] + "=" + Arr[i][1]);
        }

        System.out.println( "==================================" );
        System.out.println( "==================================" );
        System.out.println( "==================================" );
        System.out.println( "==================================" );
        System.out.println( "==================================" );



        for(i=1; i<Arr.length; i++){
            int dt, bt;
            dt=abs(Arr[i][0]-Arr[i-1][0]);
            bt=abs(Arr[i][1]-Arr[i-1][1]);
            if(abs(dt-bt)<5) ctrl++;
        }
        if (ctrl>=(0.2*Arr.length))
            System.out.println( "true" );
        else System.out.println( "false" );

    }

/*
Сделал:
Распределить по блокам
сравнить все отпечатки и вывести время
отсортировать попорядку

Осталось сделать:

переделать лист в массив для более быстрого обращения к элементам,
удалить случайные совпадения (2964=730, 286=3644),
проверить ритм
учесть пустоту и вставки
*/

}

class Audio{

    public static HashMap AudioFile(String name) throws FileNotFoundException {

        File file = new File(name);
        Scanner scanner = new Scanner(file);
        ArrayList<Integer> list = new ArrayList<Integer>();
        Map<Integer, ArrayList> hashmap = new HashMap<Integer, ArrayList>();

        String[] array = scanner.nextLine().split(" ");

        Integer str = 0;
        Integer time = 0;
        Integer reload = 0;

        for (int i = 0; i < array.length; i+=2){

            //System.out.println(array[i]);

            str=Integer.parseInt(array[i]);
            time=Integer.parseInt(array[i+1]);

            if ((time.equals(reload)==true) && (time!=0)){

                list.add(str);
                hashmap.put(time, list);
            }
            else if (time!=0){
                list = new ArrayList<Integer>();
                list.add(str);
                hashmap.put(time, list);
            }
            reload=time;
        }


        return (HashMap) hashmap;
    }

}


