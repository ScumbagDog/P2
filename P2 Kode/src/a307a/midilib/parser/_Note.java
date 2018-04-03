package a307a.midilib.parser;

class _Note implements Note{
	int	pitch;
	int velocity;
	long tick;
	long duration;
	long melodicDuration;

	public _Note(int pitch, int velocity, long tick) {
		this.pitch = pitch;
		this.velocity = velocity;
		this.tick = tick;
	}

	public void setPitch(int pitch){
		this.pitch = pitch;
	}

	public void setVelocity(int velocity){
		this.velocity = velocity;
	}

	public void setTick(int tick){
		this.tick = tick;
	}

	public void setDuration(long duration){
		this.duration = duration;
	}

	@Override
	public int getPitch(){
		return 0;
	}

	@Override
	public int getVelocity(){
		return 0;
	}

	@Override
	public double getBeat(){
		return 0;
	}

	@Override
	public double getDuration(){
		return 0;
	}

	@Override
	public double getMelodicDuration(){
		return 0;
	}

	@Override
	public int getTick(){
		return 0;
	}

	@Override
	public int getTickDuration(){
		return 0;
	}

	@Override
	public int getMelodicTickDuration(){
		return 0;
	}

	@Override
	public int getChannel(){
		return 0;
	}
}
