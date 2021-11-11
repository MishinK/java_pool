public interface UsersList {
	public void addUser(User u);
	public User user_byId(Integer id) throws Exception, UserNotFoundException;
	public User user_byIdx(Integer idx);
	public Integer number_users();
}
