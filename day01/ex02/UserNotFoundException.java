public class UserNotFoundException extends InterruptedException {
    UserNotFoundException() {
        super("user not found");
    }
}
