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
	
	RobotData [][] roboty;
	int [][] przeszkody;
	int [][] mineraly;
	int [][] gradient;
	
	
	protected void setup()
	{
		przeszkody = new int[21][21];
		roboty = new  RobotData [21][21];
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
		
		addBehaviour(new PrzeszkodaSesnsor());
		
		
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
						MoveRobot(data);
					}
					else
					{
						response = new ACLMessage(ACLMessage.REFUSE);
					}
					
					response.addReceiver (msg.getSender());
					
					myAgent.send(response);
					
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
			
			}
			
		}
		
		public Boolean CanMove(RobotData data)
		{
			Boolean wynik = false;
			
			try
			{
			switch(data.pozycja.kierunek)
			{
				case Dó³:
				{
					if(wynik = IsPrzeszkoda(data.pozycja.x + 1, data.pozycja.y) == false);
						wynik = IsRobot(data.pozycja.x + 1, data.pozycja.y);
					break;
				}
				
				case Góra:
				{
					if(wynik = IsPrzeszkoda(data.pozycja.x - 1, data.pozycja.y) == false);
						wynik = IsRobot(data.pozycja.x - 1, data.pozycja.y);
					break;
				}
				
				case Lewo:
				{
					if(wynik = IsPrzeszkoda(data.pozycja.x, data.pozycja.y - 1) == false);
						wynik = IsRobot(data.pozycja.x, data.pozycja.y - 1 );
					break;
				}
				
				case Prawo:
				{
					if(wynik = IsPrzeszkoda(data.pozycja.x, data.pozycja.y + 1) == false);
						wynik = IsRobot(data.pozycja.x, data.pozycja.y + 1 );
					break;
				}
			}
			
			return wynik;
			}
			catch (IndexOutOfBoundsException e) {
				return false;
			}
				
			}
		}
		
		public Boolean IsPrzeszkoda(int x, int y)
		{
			if(przeszkody[x][y] == 0)
				return true;
			else
				return false;
		}
		
		public Boolean IsRobot(int x, int y)
		{
			if(roboty[x][y] == null)
				return true;
			else
				return false;
		}
	
		public void MoveRobot(RobotData data)
		{
			RobotData robot = roboty[data.pozycja.x][data.pozycja.y];
			
			roboty[data.pozycja.x][data.pozycja.y] = null;
			
			switch(data.pozycja.kierunek)
			{
				case Dó³:
				{
					roboty[data.pozycja.x - 1 ][data.pozycja.y] = robot;
					break;
				}
				
				case Góra:
				{
					roboty[data.pozycja.x + 1][data.pozycja.y] = robot;
					break;
				}
				
				case Lewo:
				{
					roboty[data.pozycja.x][data.pozycja.y - 1] = robot;
					break;
				}
				
				case Prawo:
				{
					roboty[data.pozycja.x][data.pozycja.y + 1] = robot;
					break;
				}
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
