import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.abs;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Map<Integer, ArrayList> hashmapOne, hashmapTwo ;
        Audio one = new Audio();
        hashmapOne=one.AudioFile("C://Users//nicem//OneDrive//Рабочий стол//test//fp//4579522");
        Set<Map.Entry<Integer, ArrayList>> setOne = hashmapOne.entrySet();


        Audio two = new Audio();
        hashmapTwo=two.AudioFile("C://Users//nicem//OneDrive//Рабочий стол//test//fp//4579522_36");
        Set<Map.Entry<Integer, ArrayList>> setTwo = hashmapTwo.entrySet();

        ArrayList<Integer> listOne;
        ArrayList<Integer> listTwo;
        boolean check=false;
        ArrayList<Integer> ArrOne = new ArrayList<Integer>();
        ArrayList<Integer> ArrTwo = new ArrayList<Integer>();



        for (Map.Entry<Integer, ArrayList> x : setOne){
            int delta=Integer.MAX_VALUE;
            for (Map.Entry<Integer, ArrayList> y : setTwo) {
                listOne=x.getValue();
                listTwo=y.getValue();
                for (int ValueOne:listOne) {

                    for (int ValueTwo:listTwo){
                        if (ValueOne==ValueTwo){
                            if(delta>abs(x.getKey()-y.getKey())) {

                                delta=abs(x.getKey()-y.getKey());
                                if ((ArrOne.contains(x.getKey())==true)) {
                                    int a = ArrOne.indexOf(x.getKey());
                                    ArrTwo.set(a, y.getKey());
                                }else {
                                    ArrOne.add(x.getKey());
                                    ArrTwo.add(y.getKey());
                                }
                            }

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


        for ( i = 0; i<n; i++){
            System.out.println(Arr[i][0] + "=" + Arr[i][1]);
        }

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

    public static TreeMap AudioFile(String name) throws FileNotFoundException {

        File file = new File(name);
        Scanner scanner = new Scanner(file);
        ArrayList<Integer> list = new ArrayList<Integer>();

        TreeMap<Integer, ArrayList> Map = new TreeMap<Integer, ArrayList>();

        String[] array = scanner.nextLine().split(" ");

        Integer str = 0;
        Integer time = 0;
        Integer reload = 0;

        for (int i = 0; i < array.length; i+=2){


            str=Integer.parseInt(array[i]);
            time=Integer.parseInt(array[i+1]);

            if ((time.equals(reload)==true) && (time!=0)){

                list.add(str);
                Map.put(time, list);
            }
            else if (time!=0){
                list = new ArrayList<Integer>();
                list.add(str);
              Map.put(time, list);
            }
            reload=time;
        }


        return (TreeMap) Map;
    }

}


