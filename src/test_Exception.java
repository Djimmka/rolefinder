public class test_Exception {

    public interface RobotConnection extends AutoCloseable {
        void moveRobotTo(int x, int y);
        @Override
        void close();
    }

    public interface RobotConnectionManager {
        RobotConnection getConnection();
    }

    public class RobotConnectionException extends RuntimeException {

        public RobotConnectionException(String message) {
            super(message);

        }

        public RobotConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

public void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) throws RobotConnectionException {
    int j = 0;
    RobotConnection rC = null;
    for (int i = 0; i < 3; i++) {
        try {
            rC = robotConnectionManager.getConnection();
            rC.moveRobotTo(toX, toY);
            break;
        } catch (RobotConnectionException e) {
            j++;
        } finally {
            try {
                rC.close();
            } catch (Exception e) {
            }
        }
    }
    if (j == 3) {
        throw new RobotConnectionException("Ошибка связи с роботом");
    }
}
}
