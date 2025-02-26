public class User implements Authentication {
    protected String name;
    protected String sex;
    protected String phone;
    protected String email;
    protected String password;

    public User(String name, String sex, String phone, String email, String password) {
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public User() {}

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phone;
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    @Override
    public void login() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void register() {
        // TODO Auto-generated method stub
        
    }
    
}
