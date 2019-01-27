import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.abs;

public class Main {

    public static void main(String[] args)  throws FileNotFoundException{

        long start = System.currentTimeMillis();


        File dir = new File("C://Users//nicem//OneDrive//Рабочий стол//test//fp_test");

        if(dir.isDirectory())
        {
            for(File item : dir.listFiles()){

                for(File Name_File_Two : dir.listFiles()){

                    if(Name_File_Two.getName()==item.getName()){

                        System.out.println("identical files");
                    }
                    else{

                        System.out.println(item.getName() + "   " + Name_File_Two.getName() + "   " + comparison(item.getAbsolutePath(), Name_File_Two.getAbsolutePath()));

                    }
                }
            }
        }




        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println(timeConsumedMillis);

    }

    public static int comparison(String File_One_Name, String File_Two_Name)  throws FileNotFoundException{

        Map<Integer, ArrayList> hashmapOne, hashmapTwo ;

        Audio one = new Audio();
        hashmapOne=one.AudioFile(File_One_Name);

        Audio two = new Audio();
        hashmapTwo=two.AudioFile(File_Two_Name);

        Set<Map.Entry<Integer, ArrayList>> Tree_Map_One = hashmapOne.entrySet();
        Set<Map.Entry<Integer, ArrayList>> Tree_Map_Two = hashmapTwo.entrySet();

        ArrayList<Integer> Hash_Value_One;
        ArrayList<Integer> Hash_Value_Two;
        int check=0;
        boolean equally=false;
        ArrayList<Integer> True_Key_One = new ArrayList<Integer>();
        ArrayList<Integer> True_Key_Two = new ArrayList<Integer>();



        for (Map.Entry<Integer, ArrayList> Key_One : Tree_Map_One){
            int delta=Integer.MAX_VALUE;

            for (Map.Entry<Integer, ArrayList> Key_Two : Tree_Map_Two) {
                Hash_Value_One=Key_One.getValue();
                Hash_Value_Two=Key_Two.getValue();
                check=0;

                for (int ValueOne:Hash_Value_One) {

                    for (int ValueTwo:Hash_Value_Two)
                    {
                        if (ValueOne==ValueTwo){

                            if(delta>abs(Key_One.getKey()-Key_Two.getKey()) && check>=3) {

                                delta=abs(Key_One.getKey()-Key_Two.getKey());

                                if ((True_Key_One.contains(Key_One.getKey())==true)) {
                                    int a = True_Key_One.indexOf(Key_One.getKey());
                                    True_Key_Two.set(a, Key_Two.getKey());
                                }
                                else {
                                    True_Key_One.add(Key_One.getKey());
                                    True_Key_Two.add(Key_Two.getKey());
                                }
                            }
                            equally=true;
                        }

                        if (equally==true) {
                            equally=false;
                            check++;
                            break;
                        }
                    }
                    if (check==4) break;
                }

            }
        }

        return Кhythm_Сheck(True_Key_One, True_Key_Two);
    }

    public static int Кhythm_Сheck(ArrayList True_Key_One, ArrayList True_Key_Two){

        ListIterator<Integer> True_Key_One_Iter = True_Key_One.listIterator();
        ListIterator<Integer> True_Key_Two_Iter = True_Key_Two.listIterator();

        int Next_Key_One_Value=0, Next_Key_Two_Value=0, ctrl=0, itog=0;

        while(True_Key_One_Iter.hasNext() && True_Key_Two_Iter.hasNext()){

            int dt, bt, True_Key_One_Value, True_Key_Two_Value, True_Key_One_Value_Next, True_Key_Two_Value_Next;

            if (Next_Key_One_Value==0 && Next_Key_Two_Value==0) {
                True_Key_One_Value = True_Key_One_Iter.next();
                True_Key_Two_Value = True_Key_Two_Iter.next();
                True_Key_One_Value_Next = True_Key_One_Iter.next();
                True_Key_Two_Value_Next = True_Key_Two_Iter.next();
            }
            else {
                True_Key_One_Value = Next_Key_One_Value;
                True_Key_Two_Value = Next_Key_Two_Value;
                True_Key_One_Value_Next = True_Key_One_Iter.next();
                True_Key_Two_Value_Next = True_Key_Two_Iter.next();
            }

            //System.out.println(True_Key_One_Value + " = " +  True_Key_Two_Value);

            dt=abs(True_Key_One_Value_Next-True_Key_One_Value);
            bt=abs(True_Key_Two_Value_Next-True_Key_Two_Value);
            if(abs(dt-bt)<5) ctrl++;

            Next_Key_One_Value=True_Key_One_Value_Next;
            Next_Key_Two_Value=True_Key_Two_Value_Next;

        }

        if (ctrl>=(0.2*True_Key_One.size()))
            //System.out.println( "true = " + ctrl);
            itog=1;
        else
            //System.out.println( "false = " + ctrl);
            itog=0;
        return itog;
    }



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


