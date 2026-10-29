interface Switchable{
    void on();
    void off();
    default void toggle(){
        System.out.println("Device toggled");
    }
}
class Fan implements  Switchable{
    public void on(){
        System.out.println("Fan ON");

    }
    public void off()
    {
        System.out.println("Fan OFF");
    }
}
class Light implements  Switchable{
    public void on(){
        System.out.println("Light ON");
    }
    public void off()
    {
        System.out.println("Light OFF");

    }
}
@FunctionalInterface
interface permission{ 
    boolean doTask(String device ,int hour);

}
public class Remotecontrol 
{
    public static void main(String [] args)
    { 
        Switchable[] s1={ new Fan(), new Light()};
        for (Switchable s:s1)
            {
                s.toggle();
            }

    

permission p1=new permission()
{
    public boolean doTask(String device,int hour)
    {
        return hour>6;
    }

};

permission p2 = (device,hour) -> { return hour>6;};
boolean result1 = p2.doTask("Fan",8);
boolean result2 = p1.doTask("Light",12);
boolean result3 = p2.doTask("Fan",6);
boolean result4 = p2.doTask("Fan",0);
System.out.println("Permission:"  + result1);
System.out.println("Permission:"  + result2);
System.out.println("Permission:"  + result3);
System.out.println("Permission:"  + result4);
System.out.println("25AIML039");

}