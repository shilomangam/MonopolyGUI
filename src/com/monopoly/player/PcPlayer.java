package com.monopoly.player;
import java.util.ArrayList;

import com.monopoly.data.Card;
import com.monopoly.data.City;
import com.monopoly.data.Transportation;
import com.monopoly.data.Utility;
import com.monopoly.utility.GameConstants;

public class PcPlayer implements Player{

	private String playerName;
	private boolean bankrupt;
	private boolean isInJail;
	private int money;
	private boolean hasFreeJailCard;
	private int position;
	private boolean isParked;
	private Card freeJailCard;
	private ArrayList<City> playerCities;
	private ArrayList<Transportation> playerTrans;
	private ArrayList<Utility> playerUtil;
	
	public PcPlayer(String playerName, int position) {
		this.playerName = playerName;
		this.money = GameConstants.INITIAL_MONEY;
		this.isInJail = false;
		this.isParked = false;
		this.position = position;
		this.hasFreeJailCard = false;
		this.bankrupt = false;
		playerCities = new ArrayList<>();
		playerTrans = new ArrayList<>();
		playerUtil = new ArrayList<>();
		this.freeJailCard = null;
		
	}

	public String toString(){
		return playerName + " Created with " + Integer.toString(this.money) + "$ and currently on cell BASE";
	}
	public boolean canPlay(){
		return this.isInJail && this.isBankrupt() && this.isParked;
	}

	public boolean isBankrupt(){
		return bankrupt;
	}
	public boolean isInJail(){
		return this.isInJail;
	}
	public void setInJail(boolean val){
		this.isInJail = val;
	}
	public boolean isParked(){
		return this.isParked;
	}
	
	@Override
	public void setIsParked(boolean val) {
		this.isParked = val;
		
	}
	public int getPosition(){
		return this.position;
	}
	@Override
	public void setPosition(int position){
		this.position = position;
	}

	public ArrayList<City> getPlayerCities() {
		return playerCities;
	}

	public void setPlayerCities(ArrayList<City> playerCities) {
		this.playerCities = playerCities;
	}

	public ArrayList<Transportation> getPlayerTrans() {
		return playerTrans;
	}

	public void setPlayerTrans(ArrayList<Transportation> playerTrans) {
		this.playerTrans = playerTrans;
	}

	public ArrayList<Utility> getPlayerUtil() {
		return playerUtil;
	}

	public void setPlayerUtil(ArrayList<Utility> playerUtil) {
		this.playerUtil = playerUtil;
	}

	@Override
	public String getPlayerName() {
		return this.playerName;
	}
	@Override
	public void setMoney(int money) {
		this.money = money;
		
	}


	@Override
	public int getMoney() {
		return money;
	}

//	@Override
//	public boolean getAnswer() {
//		return GameView.getAnswerFromPcPlayer();
//		
//	}

	@Override
	public void setIsBankrupt(boolean b) {
		this.bankrupt = b;
		
	}

	public boolean isHasFreeJailCard() {
		return hasFreeJailCard;
	}

	public void setHasFreeJailCard(boolean hasFreeJailCard) {
		this.hasFreeJailCard = hasFreeJailCard;
	}

	@Override
	public void setJailFreeCard(Card freeJailCard) {
		this.freeJailCard = freeJailCard;
		
	}

	@Override
	public Card getJailFreeCard() {
		return this.freeJailCard;
	}

	@Override
	public ArrayList<Transportation> getTransportation() {
		return this.playerTrans;
	}


	@Override
	public ArrayList<Utility> getUtilites() {
		return this.playerUtil;
	}

	
}
	