package a307a.midilib.parser;

class Note implements INote{
	int	pitch;
	int velocity;
	long tick;

	public Note(int pitch, int velocity, long tick){
		this.pitch = pitch;
		this.velocity = velocity;
		this.tick = tick;
	}

	@Override
	public int getPitch(){
		return pitch;
	}

	@Override
	public int getVelocity(){
		return velocity;
	}

	@Override
	public long getTick(){
		return tick;
	}
}
