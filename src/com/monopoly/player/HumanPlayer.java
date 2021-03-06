package com.monopoly.player;

import java.util.ArrayList;
import com.monopoly.utility.*;

import com.monopoly.data.Card;
import com.monopoly.data.City;
import com.monopoly.data.Transportation;
import com.monopoly.data.Utility;

public class HumanPlayer implements Player {
	private PlayerData data;
	private boolean isInJail;
	private boolean bankrupt;
	private int money;
	private boolean hasFreeJailCard;
	private int position;
	private boolean isParked;
	private Card freeJailCard;
	private ArrayList<City> playerCities;
	private ArrayList<Transportation> playerTrans;
	private ArrayList<Utility> playerUtil;
	private int lastFine;
	private Player paidTo;

	public HumanPlayer(PlayerData data) {
		this.data = data;
		this.money = GameConstants.INITIAL_MONEY;
		this.isInJail = false;
		this.isParked = false;
		this.bankrupt = false;
		this.hasFreeJailCard = false;
		this.position = GameConstants.START;
		playerCities = new ArrayList<>();
		playerTrans = new ArrayList<>();
		playerUtil = new ArrayList<>();
		this.freeJailCard = null;
	}

	public boolean canPlay() {
		return this.isInJail && this.isBankrupt() && this.isParked;
	}

	public boolean isBankrupt() {
		return this.bankrupt;
	}

	public boolean isInJail() {
		return this.isInJail;
	}

	public void setInJail(boolean val) {
		this.isInJail = val;
	}

	public boolean isParked() {
		return this.isParked;
	}

	public void setIsParked(boolean val) {
		this.isParked = val;
	}

	@Override
	public void setPosition(int position) {
		this.position = position;
	}

	public int getPosition() {
		return this.position;
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

	public String getPlayerName() {
		return this.data.getName();
	}

	@Override
	public void setMoney(int money) {
		this.money = money;

	}

	@Override
	public int getMoney() {
		return money;
	}

	// @Override
	// public boolean getAnswer() {
	// return GameView.getAnswerFromRealPlayer();
	// }

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

	@Override
	public PlayerData getData() {
		return this.data;
	}
	@Override
	public int getLastFine() {
		return lastFine;
	}

	@Override
	public void setLastFine(int lastFine, Player PlayerPaidTo) {
		this.lastFine = lastFine;
		this.paidTo = PlayerPaidTo;
	}

	@Override
	public String getPaidPlayerName() {
		return this.paidTo.getPlayerName();
	}
	@Override
	public void releasePlayerAssets() {
		for (City city : playerCities) {
			city.getCell().setHasOwner(false);
			city.getCell().setOwner(null);
		}
		for (Utility util : playerUtil) {
			util.getCell().setHasOwner(false);
			util.getCell().setOwner(null);
		}
		for (Transportation trans : playerTrans) {
			trans.getCell().setHasOwner(false);
			trans.getCell().setOwner(null);
		}
		this.playerCities.clear();
		this.playerUtil.clear();
		this.playerTrans.clear();
	}

}
