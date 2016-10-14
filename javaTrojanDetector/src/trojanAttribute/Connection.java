package trojanAttribute;

public class Connection {
	private int ConnectionId;
    private int source;
    private int target;
    private boolean direct;
    
    public Connection(int source_, int destination_, boolean direct_)
    {
        this.source = source_;
        this.target = destination_;
        this.direct = direct_;
    }
    public Connection()
    {

    }
	public int getConnectionId() {
		return ConnectionId;
	}
	public void setConnectionId(int connectionId) {
		ConnectionId = connectionId;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public boolean isDirect() {
		return direct;
	}
	public void setDirect(boolean direct) {
		this.direct = direct;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ConnectionId;
		result = prime * result + (direct ? 1231 : 1237);
		result = prime * result + source;
		result = prime * result + target;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connection other = (Connection) obj;
		if (ConnectionId != other.ConnectionId)
			return false;
		if (direct != other.direct)
			return false;
		if (source != other.source)
			return false;
		if (target != other.target)
			return false;
		return true;
	}
    
    
}
