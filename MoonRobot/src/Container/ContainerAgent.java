package Container;


import Data.*;

import java.util.Random;
import java.util.Vector;

import javax.swing.tree.AbstractLayoutCache;

import FIPA.DateTime;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class ContainerAgent extends Agent
{
	
	int [][] roboty;
	int [][] przeszkody;
	int [][] mineraly;
	int [][] gradient;
	
	
	protected void setup()
	{
		przeszkody = new int[21][21];
		roboty = new  int [21][21];
		mineraly =new int [21][21];
		gradient = new int [21][21];
		
		Random rand = new Random();
		DateTime dt =  new DateTime();
		rand.setSeed(dt.milliseconds);
		
		//gneracja 8 przeszkud ;
		for(int i = 0; i < 8; i++)
		{
		 	int x = rand.nextInt(21);
		 	int y = rand.nextInt(21);
		 	przeszkody[x][y] = 1;
		 	System.out.println( x  + " " + y + " Przeszkoda");
		}
		
		for(int i = 0 ; i < 20; i++)
		{
			int x = rand.nextInt(21);
			int y = rand.nextInt(21);
			int count = rand.nextInt(10);
			mineraly[x][y] =  mineraly[x][y] + count;
			
			System.out.println( x  + " " + y + " Minera³ iloœæ: " + mineraly[x][y]);
		}
		
		
		
		
	}
	
	private class PrzeszkodaSesnsor extends CyclicBehaviour {

		@Override
		public void action() {
			//MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.);
			ACLMessage msg = myAgent.receive();
			if (msg != null) {
				try {
					Object RAWdata = msg.getContentObject();
					RobotData data = (RobotData) RAWdata;
					
					ACLMessage response;
					if(CanMove(data))
					{
						response = new ACLMessage(ACLMessage.AGREE);
					}
					else
					{
						response = new ACLMessage(ACLMessage.REFUSE);
					}
					
					response.addReceiver (msg.getSender());
					
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
			
			}
			
		}
		
		public Boolean CanMove(RobotData data)
		{
			return true;
		}
		
		public Boolean IsPrzeszkoda(RobotData data)
		{
			switch(data.pozycja.kierunek)
			{
				case Dó³:
				{
					
				}
				
				
			}
			
			return true;
		}
		
		public Boolean IsRobot(RobotData data)
		{
			return true;
		}
	
	}
	
	private class MineralSensor extends CyclicBehaviour{

		@Override
		public void action() {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class SygnalSensor extends CyclicBehaviour{

		@Override
		public void action() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class Okruszki extends CyclicBehaviour{

		@Override
		public void action() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}
