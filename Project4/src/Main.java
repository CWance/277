import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;



/**
 * 
 * @author Christian Wance 012306864
 *
 */
public class Main extends JFrame{
	
	public Main(Map m, Hero h, ItemGenerator i, EnemyGenerator e) {
		setBounds(10, 10, 1000, 1000);//x,y,w,h of window
		setTitle( "Star Wars" );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible( true );
		setLayout( new FlowLayout( FlowLayout.CENTER ) );
		Panel p = new Panel(m, h, i, e);
		add(p);
				
	}

	/**
	 * Gets a fight between an Enemy and Hero by generating an Enemy
	 * 
	 * @param h  Hero
	 * @param m  current Map
	 * @param eg EnemyGenerator
	 * @return Boolean
	 */
	

	/**
	 * Fight between a Hero and An Enemy
	 * 
	 * @param h Hero
	 * @param e Enemy
	 * @return boolean win
	 */
	public static boolean fight(Hero h, Enemy e) {
		boolean fighting = true;
		do {
			if (h.hasMedKit()) {
				switch (CheckInput.getIntRange(1, 3)) {
				case 1: {
					h.attack(e);
					if (e.getHP() > 0) {
						if (h.hasArmor()) {
							System.out.println(e.getName() + " attacks, but your " + h.removeFirstArmorItem()
									+ " stopped the damage and was destroyed");
						} else {
							e.attack(h);
						}
					}
					break;
				}
				case 2:
					fighting = false;
					break;
				case 3: {
					h.heal(25);
					h.removeItem("Med Kit");
					if (h.hasArmor()) {
						System.out.println(e.getName() + " attacks, but your " + h.removeFirstArmorItem()
								+ " stopped the damage and was destroyed");
					} else {
						e.attack(h);
					}
					break;
				}
				}
			} else {
				System.out.println("1. Attack\n2. Run Away");
				switch (CheckInput.getIntRange(1, 2)) {
				case 1: {
					h.attack(e);
					if (e.getHP() > 0) {
						if (h.hasArmor()) {
							System.out.println(e.getName() + " attacks, but your " + h.removeFirstArmorItem()
									+ " stopped the damage and was destroyed");
						} else {
							e.attack(h);
						}
					}
					break;
				}
				case 2:
					fighting = false;
					break;
				}
			}
		} while (h.getHP() > 0 && e.getHP() > 0 && fighting);
		return h.getHP() == 0;
	}

	/**
	 * Generates an item for a room
	 * 
	 * @param h  Hero
	 * @param m  current Map
	 * @param ig ItemGenerator
	 * @return boolean
	 */
	public static boolean itemRoom(Hero h, Map m, Item i) {
		m.removeCharAtLoc(h.getLocation());
		return h.pickUpItem(i);

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("What is your name?");
		Map map = Map.getInstance();
		Hero hero = new Hero(in.nextLine(), map);
		map.loadMap(hero.getLevel());
		
		ItemGenerator ig = ItemGenerator.getInstance();
		boolean cont = true;
		EnemyGenerator eg = EnemyGenerator.getInstance(ig);
		Main main = new Main(map, hero, ig, eg);
//			System.out.println("1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");
//			switch (CheckInput.getIntRange(1, 5)) {
//			case 1:
//				there = hero.goNorth();
//				break;
//			case 2:
//				there = hero.goSouth();
//				break;
//			case 3:
//				there = hero.goEast();
//				break;
//			case 4:
//				there = hero.goWest();
//				break;
//			case 5:
//				cont = false;
//				break;
//			}
//			if (cont) {
//				boolean win = true;
//				if (there == 'e') {
//					win = enemyRoom(hero, map, eg);
//				} else if (there == 'i') {
//					itemRoom(hero, map, ig);
//				} else if (there == 'f') {
//					System.out.println("You have found the exit door.");
//					if (hero.hasKey()) {
//						System.out.println("You use your key.");
//						hero.removeItem("Key");
//						System.out.println("... and you go to the next room");
//						hero.increaseLevel();
//						hero.increaseMaxHP(10);
//						System.out.println(hero.getName() + " leveled up! \nYou are now level " + hero.getLevel());
//						map.loadMap(hero.getLevel());
//					} else if (hero.hasHolocron()) {
//						System.out.println("You use the Force");
//						Random forceCheck = new Random();
//						if(forceCheck.nextInt(5) == 4) {
//							hero.removeItem("Holocron");
//							System.out.println("... and you go to the next room");
//							hero.increaseLevel();
//							hero.increaseMaxHP(10);
//							System.out.println(hero.getName() + " leveled up! \nYou are now level " + hero.getLevel());
//							map.loadMap(hero.getLevel());
//						}else {
//							System.out.println("... But you failed");
//						}
//						
//						
//					}
//				} else if (there == 'n') {
//					System.out.println("There was nothing here.");
//				}
//
//				if (win == false && hero.getHP() == 0) {
//					System.out.println("You Died!");
//					cont = false;
//				}
//			}
//		} while (cont);
		in.close();
	}

	class Panel extends JPanel{
		Map map;
		Hero hero;
		ItemGenerator iGen;
		EnemyGenerator eGen;
		class Board extends JPanel implements KeyListener{
			class Space extends JPanel{
				private boolean visited;
				private char thingHere;
				private JLabel lblSpace;
				public Space(boolean v, char t) {
					setPreferredSize(new Dimension(75, 75));
					visited = v;
					thingHere = t;
					lblSpace = new JLabel(""+thingHere);
					lblSpace.setVisible(visited);
					setBorder(BorderFactory.createLineBorder(Color.black));
					setBackground(Color.gray);
					add(lblSpace);
				}
				public void visited() {
					visited = true;
					lblSpace.setVisible(visited);
					setBackground(Color.white);
					//return thingHere;
				}
				
				
			}
			private Space[][] spaces;
			private SpaceEvent s;
			Map m;
			Hero h;
			public Board(SpaceEvent s) {
				this.s = s;
				setLayout(new GridLayout(5,5));
				spaces = new Space[5][5];
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 5; j++) {
						spaces[i][j] = new Space(false,map.getCharAtLoc(new Point(i,j)));
					}
				}
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 5; j++) {
						add(spaces[i][j]);
					}
				}
				addKeyListener( this );
				setFocusable( true );
				
			}
			public void visit(Point p) {
				spaces[p.x][p.y].visited();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				
		
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if( e.getKeyCode() == KeyEvent.VK_UP ) {
					s.setEvent(hero.goNorth());
					spaces[hero.getLocation().x][hero.getLocation().y].visited();
				}
				if( e.getKeyCode() == KeyEvent.VK_DOWN ) {
					hero.goSouth();
					spaces[hero.getLocation().x][hero.getLocation().y].visited();
				}
				if( e.getKeyCode() == KeyEvent.VK_LEFT ) {
					hero.goWest();
					spaces[hero.getLocation().x][hero.getLocation().y].visited();
				}
				if( e.getKeyCode() == KeyEvent.VK_RIGHT ) {
					hero.goEast();
					spaces[hero.getLocation().x][hero.getLocation().y].visited();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		}
		class HeroInfo extends JPanel{
			private JLabel lblName, lblLvl, lblHp, lblInventory;
			private ArrayList<Inventory> inventory;
			class Inventory extends JPanel{
				private String thingHere;
				private JLabel lblInv;
				public Inventory(String i) {
					setPreferredSize(new Dimension(100, 100));
					thingHere = i;
					lblInv = new JLabel(thingHere);
					lblInv.setVisible(false);
					setBorder(BorderFactory.createLineBorder(Color.black));
					add(lblInv);
					
				}
				public void insert(String n) {
					thingHere = n;
					lblInv.setText(n);
					lblInv.setVisible(true);
					setBackground(Color.white);
				}
				
				public String getInvName() {
					return thingHere;
				}
				
				public void remove(){
					lblInv.setText("");
					lblInv.setVisible(false);
				}
			
				
			}
			public HeroInfo() {
				setLayout( new FlowLayout( FlowLayout.CENTER ) );
				lblName = new JLabel("Name: " + hero.getName());
				lblLvl = new JLabel("Level: " + hero.getLevel());
				lblHp = new JLabel("HP: " + hero.getHP() + "/" + hero.getMaxHP());
				lblInventory = new JLabel("Inventory: ");
				add(lblName);
				add(lblLvl);
				add(lblHp);
				add(lblInventory);
				JPanel inv = new JPanel();
				inventory = new ArrayList<Inventory>();
				for(int i = 0; i < 5; i++) {
					inventory.add( new Inventory(""));
					inv.add(inventory.get(i));
				}
				setBorder(BorderFactory.createLineBorder(Color.black));
				inv.setLayout(new FlowLayout(FlowLayout.LEFT));
				add(inv);
				setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
				
			}
		}
		class SpaceEvent extends JPanel implements ActionListener,KeyListener{
			
			private JLabel lblEventName, lblEvent, lblEnemyHp;
			private JButton medKitBtn,fightBtn,fightNormalBtn,fightForceBtn,forcePushBtn,forceChokeBtn,forceSlamBtn, runBtn, dropFoundBtn, dropInventoryBtn;
			private HeroInfo hi;
			Enemy enemy;
			public SpaceEvent(HeroInfo h) {
				hi = h;
				lblEventName = new JLabel("Entry");
				lblEvent = new JLabel("You enter the area");
				lblEnemyHp = new JLabel();
				fightBtn = new JButton("Fight!");
				runBtn = new JButton("Run Away");
				medKitBtn = new JButton("Use Med Kit");
				fightNormalBtn = new JButton("Use Blaster");
				fightForceBtn = new JButton("Use Force");
				forcePushBtn = new JButton("Use Force Push");
				forceSlamBtn = new JButton("Use Force Slam");
				forceChokeBtn = new JButton("Use Force Choke");				
				dropFoundBtn = new JButton("Drop Found Item");
				dropInventoryBtn = new JButton("Drop Inventory Item");
				JPanel title = new JPanel();
				
				title.add(lblEventName);
				title.setBorder(BorderFactory.createLineBorder(Color.black));
				add(title);
				add(lblEvent);
				add(lblEnemyHp);
				add(fightBtn);
				add(runBtn);
				add(medKitBtn);
				add(fightNormalBtn);
				add(fightForceBtn);
				add(forcePushBtn);
				add(forceSlamBtn);
				add(forceChokeBtn);				
				add(dropFoundBtn);
				add(dropInventoryBtn);
				fightBtn.setVisible(false);
				runBtn.setVisible(false);
				medKitBtn.setVisible(false);
				fightNormalBtn.setVisible(false);
				fightForceBtn.setVisible(false);
				forcePushBtn.setVisible(false);
				forceSlamBtn.setVisible(false);
				forceChokeBtn.setVisible(false);				
				dropFoundBtn.setVisible(false);
				dropInventoryBtn.setVisible(false);
				setBorder(BorderFactory.createLineBorder(Color.black));
				setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
				
				
			}
			
			public void setEvent(char e) {
				boolean win = true;
				if (e == 'e') {
					falseAll();
					enemy = eGen.generateEnemy(hero.getLevel());
					
					lblEventName.setText("An Enemy!");
					lblEvent.setText("You've encountered a lvl " + enemy.getLevel() + " " + enemy.getName());
					lblEnemyHp.setText("" + enemy.getHP() + "/" + enemy.getMaxHP());
					fightBtn.setVisible(true);
					runBtn.setVisible(true);
				} else if (e == 'i') {
					falseAll();
					Item i = iGen.generateItem();
					lblEventName.setText("An Item!");
					lblEvent.setText("You found an " + i.getName());
					int j = 0;
					while(j< 5) {
						if(hi.inventory.get(j).getInvName().equals("")) {
							hi.inventory.get(j).insert(i.getName());
						}
						j++;
					}
					if(hero.getNumItems() == 5) {
						
					}
					itemRoom(hero, map, i);
				} else if (e == 'f') {
					System.out.println("You have found the exit door.");
					if (hero.hasKey()) {
						System.out.println("You use your key.");
						hero.removeItem("Key");
						System.out.println("... and you go to the next room");
						hero.increaseLevel();
						hero.increaseMaxHP(10);
						System.out.println(hero.getName() + " leveled up! \nYou are now level " + hero.getLevel());
						map.loadMap(hero.getLevel());
					} else if (hero.hasHolocron()) {
						System.out.println("You use the Force");
						Random forceCheck = new Random();
						if(forceCheck.nextInt(5) == 4) {
							hero.removeItem("Holocron");
							System.out.println("... and you go to the next room");
							hero.increaseLevel();
							hero.increaseMaxHP(10);
							System.out.println(hero.getName() + " leveled up! \nYou are now level " + hero.getLevel());
							map.loadMap(hero.getLevel());
						}else {
							System.out.println("... But you failed");
						}
						
						
					}
				} else if (e == 'n') {
					System.out.println("There was nothing here.");
				}
			}
			
			private void falseAll() {
				fightBtn.setVisible(false);
				runBtn.setVisible(false);
				fightNormalBtn.setVisible(false);
				fightForceBtn.setVisible(false);
				forcePushBtn.setVisible(false);
				forceSlamBtn.setVisible(false);
				forceChokeBtn.setVisible(false);				
				dropFoundBtn.setVisible(false);
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals(fightBtn.getText())) {
					falseAll();
					fightNormalBtn.setVisible(true);
					fightForceBtn.setVisible(hero.hasHolocron());
					medKitBtn.setVisible(hero.hasMedKit());
					
				}
				if(e.getSource() == runBtn) {
					
				}
				if(e.getSource() == fightNormalBtn) {
					
				}
				if(e.getSource() == fightForceBtn) {
					
				}
				if(e.getSource() == forcePushBtn) {
					
				}
				if(e.getSource() == forceSlamBtn) {
					
				}
				if(e.getSource() == forceChokeBtn) {
					
				}
				if(e.getSource() == dropFoundBtn) {
					
				}

					
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if( e.getKeyCode() == KeyEvent.VK_1 ) {
					hero.removeItem(1);
					hi.inventory.get(1).remove();
				}
				if( e.getKeyCode() == KeyEvent.VK_2 ) {
					hero.removeItem(2);
				}
				if( e.getKeyCode() == KeyEvent.VK_3 ) {
					hero.removeItem(3);
				}
				if( e.getKeyCode() == KeyEvent.VK_4 ) {
					hero.removeItem(4);
				}
				if( e.getKeyCode() == KeyEvent.VK_5 ) {
					hero.removeItem(5);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		}
		
		public Panel(Map m, Hero h, ItemGenerator ig, EnemyGenerator eg) {
			map = m;
			hero = h;
			iGen = ig;
			eGen = eg;
			
			HeroInfo hi = new HeroInfo();
			SpaceEvent s = new SpaceEvent(hi);
			Board b = new Board(s);
			JPanel i = new JPanel();
			add(b);
			i.add(hi);
			i.add(s);
			add(i);
			
			setLayout(new FlowLayout(FlowLayout.LEFT));
			b.visit(map.findStart());
			
		}
		public void BoardVisit() {
			
		}
	}
		
	
	
}

		
	


