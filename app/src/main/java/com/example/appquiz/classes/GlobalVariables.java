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
        Test t1 = new Test(R.drawable.eye,"a","What is this sense?","Sight","Smell");
        Test t2 = new Test(R.drawable.nose2,"b","What is this sense?","Sight","Smell");
        Test t3 = new Test(R.drawable.hand2,"b","What is this sense?","Hear","Touch");
        Test t4 = new Test(R.drawable.tongue2,"a","What is this sense?","Taste","Touch");
        Test t5 = new Test(R.drawable.ear_2,"a","What is this sense?","Hear","Touch");

        Test t6 = new Test(0,"a","What comes after A?","B","C");
        Test t7 = new Test(0,"a","What comes after X?","Y","Z");
        Test t8 = new Test(0,"b","What comes after S?","R","T");
        Test t9 = new Test(0,"a","What comes after T?","U","V");
        Test t10 = new Test(0,"b","What comes after V?","U","W");

        Test t11 = new Test(0,"a","What is 2 + 2?","4","22");
        Test t12 = new Test(0,"a","What is 50 + 50?","100","55");
        Test t13 = new Test(0,"b","What is 10 + 20?","12","30");
        Test t14 = new Test(0,"a","What is 5 + 6?","11","56");
        Test t15 = new Test(0,"b","What is 23 + 12?","45","35");
        Test t16 = new Test(0,"a","What is 45 - 14?","31","41");
        Test t17 = new Test(0,"a","What is 50 - 10?","40","49");
        Test t18 = new Test(0,"b","What is 10 - 4?","14","6");
        Test t19 = new Test(0,"a","What is 99 - 9?","90","98");
        Test t20 = new Test(0,"b","What is 66 - 11?","65","55");

        Test t21 = new Test(R.drawable.eye,"b","What is this?","Nail","Eyes");
        Test t22 = new Test(R.drawable.nose2,"a","What is this?","Nose","Eyes");
        Test t23 = new Test(R.drawable.tongue2,"b","What is this?","Nose","Tongue");
        Test t24 = new Test(R.drawable.lip,"a","What is this?","Lips","Tongue");
        Test t25 = new Test(R.drawable.hand2,"b","What is this?","Lips","Hand");

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
        Lesson s1 = new Lesson("Tongue for Taste",R.drawable.tongue2);
        sensesArrayList.add(s1);
        Lesson s2 = new Lesson("Ears for Hearing",R.drawable.ear_2);
        sensesArrayList.add(s2);
        Lesson s3 = new Lesson("Eyes for Sight",R.drawable.eye);
        sensesArrayList.add(s3);
        Lesson s4 = new Lesson("Nose for Smell",R.drawable.nose2);
        sensesArrayList.add(s4);
        Lesson s5 = new Lesson("Skin for Touch",R.drawable.hand2);
        sensesArrayList.add(s5);
    }
    public ArrayList<Lesson> getSenses(){
        return sensesArrayList;
    }
    private void addNumbers(){
        for(int i = 1; i < 101 ;i++){
            Lesson lesson = new Lesson(String.valueOf(i));
            numbersArrayList.add(lesson);
        }
    }
    public ArrayList<Lesson> getNumbers(){
        return numbersArrayList;
    }
    private void addAlphabets() {
        Lesson a = new Lesson("a for apple", R.drawable.al);
        alphabetsArrayList.add(a);
        Lesson b = new Lesson("b for ball", R.drawable.bl);
        alphabetsArrayList.add(b);
        Lesson c = new Lesson("c for cat", R.drawable.cl);
        alphabetsArrayList.add(c);
        Lesson d = new Lesson("d for dog", R.drawable.dl);
        alphabetsArrayList.add(d);
        Lesson e = new Lesson("e for egg", R.drawable.el);
        alphabetsArrayList.add(e);
        Lesson f = new Lesson("f for fish", R.drawable.fl);
        alphabetsArrayList.add(f);
        Lesson g = new Lesson("g for goat", R.drawable.gl);
        alphabetsArrayList.add(g);
        Lesson h = new Lesson("h for hat", R.drawable.hl);
        alphabetsArrayList.add(h);
        Lesson i = new Lesson("i igloo", R.drawable.il);
        alphabetsArrayList.add(i);
        Lesson j = new Lesson("j for jam", R.drawable.jl);
        alphabetsArrayList.add(j);
        Lesson k = new Lesson("k for king", R.drawable.kl);
        alphabetsArrayList.add(k);
        Lesson l = new Lesson("l for lion", R.drawable.ll);
        alphabetsArrayList.add(l);
        Lesson m = new Lesson("m for moon", R.drawable.ml);
        alphabetsArrayList.add(m);
        Lesson n = new Lesson("n for nut", R.drawable.nl);
        alphabetsArrayList.add(n);
        Lesson o = new Lesson("o for orange", R.drawable.ol);
        alphabetsArrayList.add(o);
        Lesson p = new Lesson("p for pig", R.drawable.pl);
        alphabetsArrayList.add(p);
        Lesson q = new Lesson("q for queen", R.drawable.ql1);
        alphabetsArrayList.add(q);
        Lesson r = new Lesson("r for rocket", R.drawable.rl);
        alphabetsArrayList.add(r);
        Lesson s = new Lesson("s for sun", R.drawable.sl);
        alphabetsArrayList.add(s);
        Lesson t = new Lesson("t for tree", R.drawable.tl);
        alphabetsArrayList.add(t);
        Lesson u = new Lesson("u for umbrella", R.drawable.ul);
        alphabetsArrayList.add(u);
        Lesson v = new Lesson("v for van", R.drawable.vl);
        alphabetsArrayList.add(v);
        Lesson w = new Lesson("w for watch", R.drawable.wl);
        alphabetsArrayList.add(w);
        Lesson x = new Lesson("x for x-ray", R.drawable.xl1);
        alphabetsArrayList.add(x);
        Lesson y = new Lesson("y for yo-yo", R.drawable.yl);
        alphabetsArrayList.add(y);
        Lesson z = new Lesson("z for zip", R.drawable.zl);
        alphabetsArrayList.add(z);
    }

    public static ArrayList<Lesson> getAlphabets(){
        return alphabetsArrayList;
    }
}
