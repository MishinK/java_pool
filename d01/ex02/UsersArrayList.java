
public class UsersArrayList implements UsersList{
	private Integer count;
	private Integer size;
	private User[] arr = new User[10];

	public UsersArrayList(){
		this.size = 10;
		this.count = 0;
	}
	public void addUser(User u){
		if (this.count < this.size)
		{
			arr[count] = u;
		}
		else
		{
			User[] tmp = new User[this.size * 3 / 2];
			for (int i = 0; i < this.size; ++i)
				tmp[i] = arr[i];
			tmp[this.size] = u;
			this.size = this.size * 3 / 2;
			arr = tmp;
		}
		count++;
	}
	public User user_byId(Integer id) throws UserNotFoundException {
		for (int i = 0; i < this.count; ++i)
		{
			if (arr[i].getId() == id)
				return (arr[i]);
		}
		throw new UserNotFoundException();
	}
	public User user_byIdx(Integer idx){
		return (arr[idx]);
	}

	public Integer number_users(){
		return (this.count);
	}
}
