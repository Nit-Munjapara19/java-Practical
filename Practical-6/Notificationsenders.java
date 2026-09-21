public class Notificationsenders {
    public static void main(String[] args)
    {
        Notifier email = (message)->
        {
            System.out.println("E:"+message);
        };
        Notifier sms = (message)->
        {
            System.out.println("sms:"+message);
        };
        UrgentNotifier urgentEmail = new UrgentNotifier();
        Notifier[] senders = {email, sms, urgentEmail};
        for (Notifier sender : senders) {
            sender.send("Exam starts tomorrow.");
        
        if (sender instanceof Urgent) {
                sender.send("Exam starts tomorrow.");
            }
        }
    }

}
@FunctionalInterface
interface Notifier
{
    void send(String message);
}
interface Urgent
{}
class UrgentNotifier implements Notifier,Urgent
{
        public void send(String message)
        {
            System.out.println("Urgent Email: " + message);
            System.out.println("25AIML039");
        }
}