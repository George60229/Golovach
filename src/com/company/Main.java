package com.company;

public class Main {

    public static void main(String[] args) {

    }
    private static void first() throws Throwable {}{
//Kидать можем только объекты с типом Throwable и никакие другие
    }
    private static void second() throws Throwable {}{
        try {
        } catch (Throwable t) {}
        //Выполняем код, который находится в try и обрабатываем то, что нам будет выкинуто с помощью catch
    }

    private static void third(){
        // Error производный от класса Throwable
        throw new Error();
    }
    private static int fourth(){
        throw new RuntimeException();
        //Можем вообще ничего не возвщать, хотя программа ожидает int
    }
    private static int five(){
        throw new RuntimeException();
        //Можем вообще ничего не возращать, хотя программа ожидает int
    }
    private static double six(double width,double height){
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Negative sizes: w = " + width + ", h = " + height);
        }
        return width * height;
        //Если что-то пошло не по плану, можно кинуть Exception и тогда его трудно будет игнорировать, но это не повлияет на работоспособность программы
    }
    private static void seven(){
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch (Exception e) { // catch по Exception ПЕРЕХВАТЫВАЕТ RuntimeException
            System.err.print(" 2");
        }
        System.err.println(" 3");
    //Вывод будет 0,2,3 ибо 1 не пройдет, потому что было исключение и его словил catch и выполнение кода пошло туда
    }
    private static void eight(){
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                RuntimeException re = (RuntimeException) e;
                System.err.print("Это RuntimeException на самом деле!!!");
            } else {
                System.err.print("В каком смысле не RuntimeException???");
            }
        }
        //Всегда будет RuntimeException ибо мы его и кидаем
    }
    private static void nine(){
        try {
        } catch (Error e) {
        } catch (RuntimeException e) {
        }
        // catch может быть обработан только один, пока он не найдет нужное исключение
    }
    private static void ten(){
        try {
            return;
        } finally {
            System.err.println("finally");
        }
    // finally выполниться в любом случае, если не вмешаться туда физически
    }
    private static int eleven(){
        try {
            return 0;
        } finally {
            return 1;
        }
        // Вернется 1 ибо finally перебивает return
    }
    private static void twelve(){
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch(Error e) {
            System.err.print(" 2");
        } finally {
            System.err.print(" 3");
        }
        System.err.print(" 4");
    // будет 0 3 ибо до 1 не доходим из-за исключения catch тоже не подходит и нам остается зайти в finally
    }
    private static void thirteen(){
        try {
            System.err.print(" 0");
            try {
                System.err.print(" 1");
                if (true) {throw new Exception();}
                System.err.print(" 2");
            } catch (RuntimeException e) {
                System.err.print(" 3"); // НЕ заходим - есть исключение, но НЕПОДХОДЯЩЕГО ТИПА
            } finally {
                System.err.print(" 4"); // заходим всегда
            }
            System.err.print(" 5");     // не заходим - выполнение НЕ в норме
        } catch (Exception e) {
            System.err.print(" 6");     // ЗАХОДИМ - есть подходящее исключение
        } finally {
            System.err.print(" 7");     // заходим всегда
        }
        System.err.print(" 8");         // заходим - выполнение УЖЕ в норме
    }
    // Всегда заходим в finally, в catch заходим только если нам бросили такое исключение и другой catch его не словил
}
