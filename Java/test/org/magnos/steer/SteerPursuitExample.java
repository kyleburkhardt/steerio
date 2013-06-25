
package org.magnos.steer;

import java.awt.Color;
import java.awt.Graphics2D;

import org.magnos.steer.behavior.SteerDrive;
import org.magnos.steer.behavior.SteerTo;
import org.magnos.steer.behavior.SteerWander;
import org.magnos.steer.target.TargetFuture;

import com.gameprogblog.engine.Game;
import com.gameprogblog.engine.GameLoop;
import com.gameprogblog.engine.GameLoopVariable;
import com.gameprogblog.engine.GameScreen;
import com.gameprogblog.engine.GameState;
import com.gameprogblog.engine.Scene;


public class SteerPursuitExample extends SteerBasicExample
{

	public static void main( String[] args )
	{
		Game game = new SteerPursuitExample( DEFAULT_WIDTH, DEFAULT_HEIGHT );
		GameLoop loop = new GameLoopVariable( 0.1f );
		GameScreen screen = new GameScreen( DEFAULT_WIDTH, DEFAULT_HEIGHT, true, loop, game );
		screen.setBackground( Color.black );
		GameScreen.showWindow( screen, "SteerPursuitExample" );
	}
	
	private SteerSprite predator;
	private TargetFuture future;
	private SteerSprite sprite;

	public SteerPursuitExample( int w, int h )
	{
		super( w, h );
	}

	@Override
	public void start( Scene scene )
	{
		sprite = newSprite( Color.blue, 15, 300, 1000, new SteerWander( 0, 100, 150, 80 ) );

		predator = newSprite( Color.orange, 15, 200, 1000, new SteerSet(
			new SteerTo( future = new TargetFuture( sprite ) ),
			new SteerDrive( 0, 0, 0, 100, true )
		));
	}

	@Override
	public void draw( GameState state, Graphics2D gr, Scene scene )
	{
		super.draw( state, gr, scene );

		if (drawCircles)
		{
			drawCircle( gr, Color.red, future.getTarget( predator ), 8, true );	
		}
	}

}
