package com.example.appquiz.classes;

import android.app.Application;

import com.example.appquiz.R;

import java.util.ArrayList;

public class GlobalVariables extends Application {
    private static ArrayList<Lesson> alphabetsArrayList = new ArrayList<>();
    private static ArrayList<Lesson> numbersArrayList = new ArrayList<>();
    private static ArrayList<Lesson> sensesArrayList = new ArrayList<>();
    private static ArrayList<Test> testArrayList = new ArrayList<>();

    public GlobalVariables(){
        addAlphabets();
        addNumbers();
        addSenses();
        addTest();
    }
    private void addTest(){
        Test t1 = new Test(R.drawable.sight,"a","What is this sense?","Sight","Smell");
        Test t2 = new Test(R.drawable.smell,"b","What is this sense?","Sight","Smell");
        Test t3 = new Test(R.drawable.touch,"b","What is this sense?","Hear","Touch");
        Test t4 = new Test(R.drawable.taste,"a","What is this sense?","Taste","Touch");
        Test t5 = new Test(R.drawable.hear,"a","What is this sense?","Hear","Touch");

        Test t6 = new Test(0,"a","What comes after A?","B","C");
        Test t7 = new Test(0,"a","What comes after X?","Y","Z");
        Test t8 = new Test(0,"b","What comes after S?","R","T");
        Test t9 = new Test(0,"a","What comes after T?","U","V");
        Test t10 = new Test(0,"b","What comes after 5?","U","W");

        Test t11 = new Test(0,"a","What comes after 23?","24","22");
        Test t12 = new Test(0,"a","What comes after 44?","45","43");
        Test t13 = new Test(0,"b","What comes after 67?","66","68");
        Test t14 = new Test(0,"a","What comes after 225?","226","224");
        Test t15 = new Test(0,"b","What comes after 990?","989","991");

        Test t16 = new Test(R.drawable.nose,"a","What is this?","Nose","Ears");
        Test t17 = new Test(R.drawable.elbow,"b","What is this?","Nose","Elbow");
        Test t18 = new Test(R.drawable.ear,"a","What is this?","Ear","Elbow");
        Test t19 = new Test(R.drawable.hair,"b","What is this?","Ear","Hair");
        Test t20 = new Test(R.drawable.nail,"a","What is this?","Nail","Hair");
        Test t21 = new Test(R.drawable.eyes,"b","What is this?","Nail","Eyes");
        Test t22 = new Test(R.drawable.nose,"a","What is this?","Nose","Eyes");
        Test t23 = new Test(R.drawable.tongue,"b","What is this?","Nose","Tongue");
        Test t24 = new Test(R.drawable.lips,"a","What is this?","Lips","Tongue");
        Test t25 = new Test(R.drawable.hand,"b","What is this?","Lips","Hand");

        testArrayList.add(t1);
        testArrayList.add(t2);
        testArrayList.add(t3);
        testArrayList.add(t4);
        testArrayList.add(t5);
        testArrayList.add(t6);
        testArrayList.add(t7);
        testArrayList.add(t8);
        testArrayList.add(t9);
        testArrayList.add(t10);
        testArrayList.add(t11);
        testArrayList.add(t12);
        testArrayList.add(t13);
        testArrayList.add(t14);
        testArrayList.add(t15);
        testArrayList.add(t16);
        testArrayList.add(t17);
        testArrayList.add(t18);
        testArrayList.add(t19);
        testArrayList.add(t20);
        testArrayList.add(t21);
        testArrayList.add(t22);
        testArrayList.add(t23);
        testArrayList.add(t24);
        testArrayList.add(t25);

    }
    public ArrayList<Test> getTest(){
        return testArrayList;
    }
    private void addSenses(){
        Lesson s1 = new Lesson("Tongue for Taste",R.drawable.taste);
        sensesArrayList.add(s1);
        Lesson s2 = new Lesson("Ears for Hearing",R.drawable.hear);
        sensesArrayList.add(s2);
        Lesson s3 = new Lesson("Eyes for Sight",R.drawable.sight);
        sensesArrayList.add(s3);
        Lesson s4 = new Lesson("Nose for Smell",R.drawable.smell);
        sensesArrayList.add(s4);
        Lesson s5 = new Lesson("Skin for Touch",R.drawable.touch);
        sensesArrayList.add(s5);
    }
    public ArrayList<Lesson> getSenses(){
        return sensesArrayList;
    }
    private void addNumbers(){
        for(int i = 1;i<101;i++){
            Lesson lesson = new Lesson(String.valueOf(i));
            numbersArrayList.add(lesson);
        }
    }
    public ArrayList<Lesson> getNumbers(){
        return numbersArrayList;
    }
    private void addAlphabets() {
        Lesson a = new Lesson("a");
        alphabetsArrayList.add(a);
        Lesson b = new Lesson("b");
        alphabetsArrayList.add(b);
        Lesson c = new Lesson("c");
        alphabetsArrayList.add(c);
        Lesson d = new Lesson("d");
        alphabetsArrayList.add(d);
        Lesson e = new Lesson("e");
        alphabetsArrayList.add(e);
        Lesson f = new Lesson("f");
        alphabetsArrayList.add(f);
        Lesson g = new Lesson("g");
        alphabetsArrayList.add(g);
        Lesson h = new Lesson("h");
        alphabetsArrayList.add(h);
        Lesson i = new Lesson("i");
        alphabetsArrayList.add(i);
        Lesson j = new Lesson("j");
        alphabetsArrayList.add(j);
        Lesson k = new Lesson("k");
        alphabetsArrayList.add(k);
        Lesson l = new Lesson("l");
        alphabetsArrayList.add(l);
        Lesson m = new Lesson("m");
        alphabetsArrayList.add(m);
        Lesson n = new Lesson("n");
        alphabetsArrayList.add(n);
        Lesson o = new Lesson("o");
        alphabetsArrayList.add(o);
        Lesson p = new Lesson("p");
        alphabetsArrayList.add(p);
        Lesson q = new Lesson("q");
        alphabetsArrayList.add(q);
        Lesson r = new Lesson("r");
        alphabetsArrayList.add(r);
        Lesson s = new Lesson("s");
        alphabetsArrayList.add(s);
        Lesson t = new Lesson("t");
        alphabetsArrayList.add(t);
        Lesson u = new Lesson("u");
        alphabetsArrayList.add(u);
        Lesson v = new Lesson("v");
        alphabetsArrayList.add(v);
        Lesson w = new Lesson("w");
        alphabetsArrayList.add(w);
        Lesson x = new Lesson("x");
        alphabetsArrayList.add(x);
        Lesson y = new Lesson("y");
        alphabetsArrayList.add(y);
        Lesson z = new Lesson("z");
        alphabetsArrayList.add(z);
    }

    public static ArrayList<Lesson> getAlphabets(){
        return alphabetsArrayList;
    }
}
