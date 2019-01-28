package ru.sbt.mipt.oop.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.homecomponents.BasicSmartHome;
import ru.sbt.mipt.oop.remotecontrol.Controller;

@Component
public class CommandFactory {


    private final BasicSmartHome smartHome;
    private final CommandHistory history;

    @Autowired
    public CommandFactory(BasicSmartHome smartHome, CommandHistory history) {
        this.smartHome = smartHome;
        this.history = history;
    }

    public Command getCommand(CommandType type) {
        switch (type) {
            case ALL_LIGHTS_OFF:
                return new AllLightsOffCommand(smartHome);
            case ALL_LIGHTS_ON:
                return new AllLightsOnCommand(smartHome);
            case HALL_DOOR_CLOSE:
                return new HallDoorCloseCommand(smartHome);
            case HALL_LIGHTS_OFF:
                return new HallLightsOffCommand(smartHome);
            case SIGNALLING_DEACTIVATE:
                return new SignallingAlarmCommand(smartHome);
            case SIGNALLING_ACTIVATE:
                return new SignallingActivateCommand(smartHome);
            default:
                return null;

        }
    }

    public Command getUndoCommandForController(Controller controller) {

        return new UndoCommand(controller, history);
    }

    public Command getSignalingActivate(String password) {
        return new SignallingActivateCommand(smartHome, password);
    }

}
