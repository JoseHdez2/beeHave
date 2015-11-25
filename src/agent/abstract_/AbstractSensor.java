package agent.abstract_;

public interface AbstractSensor {
    
    // The sensor returns the perception it gets from the environment.
    AbstractPerception getPerception();
}
