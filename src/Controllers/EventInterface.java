package Controllers;

import java.util.TimerTask;

public interface EventInterface {
 
    public TimerTask addEvent(long delay, Runnable function); // adicionar um evento
}
