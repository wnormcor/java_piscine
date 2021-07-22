public class UserIdsGenerator {

    private static int nextUserID = 0;
    private static UserIdsGenerator Instance = new UserIdsGenerator();

    private UserIdsGenerator() {}

    public static UserIdsGenerator getInstance() {
        return Instance;
    }

    public static int generateId()
    {
        return nextUserID++;
    }

}