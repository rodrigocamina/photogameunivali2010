package engine.control;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import engine.util.Drawable;



//======================= Screen =========================
//
// Implementa a tela principal do jogo
//
//========================================================
public abstract class Screen extends Drawable{
	//Background?
	protected LinkedList<Drawable> components = new LinkedList<Drawable>();
	protected Screen subScreen;
	private Screen parentScreen;
	@Override
	public abstract void draw(Graphics2D graphics);
	
	@Override
	public abstract void simulate(long diffTime);
	
	//====================== drawAll =========================
	//
	// Desenha todos os componentes visiveis na screen
	//
	//========================================================
	public void drawAll(Graphics2D graphics){
		draw(graphics);
		try{
			for (Drawable component : components) {
				component.draw(graphics);
			}
		}catch (Exception e) {
			
		}
		if(subScreen!=null){
			subScreen.drawAll(graphics);
		}
	}
	
	//===================== simulateAll ======================
	//
	// Simula todos os componentes da screen
	//
	//========================================================
	public void simulateAll(long diffTime){
		simulate(diffTime);
		for (Drawable component : components) {
			component.simulate(diffTime);
		}
		if(subScreen!=null){
			subScreen.simulateAll(diffTime);
		}
	}
	
	//================ Componentes ==================
	//
	// Implementa a forma como serão adicionados 
	//novos componentes a screen
	//
	//===============================================
	public void addSubScreen(Screen subScreen){
		this.subScreen = subScreen;
		subScreen.setParentScreen(this);
	}
	
	public void removeSubScreen(){
		this.subScreen = null;
	}
	public void addComponent(Drawable component){
		if(!components.contains(component))
		{
			components.add(component);
		}
	}

	public void addComponentToFirst(Drawable component){
		components.addFirst(component);
	}

	public void addComponentToLast(Drawable component){
		components.addLast(component);
	}

	public void switchOrder(int index1, int index2){
		Drawable element = components.get(index2);
		components.set(index2, components.get(index1));
		components.set(index1, element);
	}
	
	public Drawable getComponent(int index){
		return components.get(index);
	}
	
	public Drawable getLastComponent(){
		return components.getLast();
	}
	
	public Screen getSubScreen() {
		return subScreen;
	}
	
	public Drawable getFirstComponent(){
		return components.getFirst();
	}
	
	public void removeComponent(int index){
		components.remove(index);
	}
	
	public void removeComponent(Drawable component){
		components.remove(component);
	}
	
	public int getComponentIndex(Drawable component){
		return components.indexOf(component);
	}
	
	public int getNumberOfComponents(){
		return components.size();
	}
	
	//======================= Eventos =========================
	//
	// Implementa os eventos de teclado da screen
	//
	//=========================================================

	void keyPressed(KeyEvent e) {
		if(subScreen!=null){
			subScreen.keyPressed(e);
		}else{
			pressedKey(e);
		}

	}
	public void pressedKey(KeyEvent e){
		
	}
	void keyReleased(KeyEvent e) {
		if(subScreen!=null){
			subScreen.keyReleased(e);
		}else{
			releasedKey(e);
		}
	}
	public void releasedKey(KeyEvent e){

	}
	void keyTyped(KeyEvent e) {
		if(subScreen!=null){
			subScreen.keyTyped(e);
		}else{
			typedKey(e);
		}
	}
	public void typedKey(KeyEvent e){
		
	}
	
	//======================= Eventos =========================
	//
	// Implementa os eventos de mouse da screen
	//
	//=========================================================
	void mouseClicked(MouseEvent e) {
		if(subScreen!=null){
			subScreen.mouseClicked(e);
		}else{
			clicked(e);
		}
	}
	public void clicked(MouseEvent e){
		
	}
	void mouseEntered(MouseEvent e) {
		if(subScreen!=null){
			subScreen.mouseEntered(e);
		}else{
			entered(e);
		}
	}
	public void entered(MouseEvent e){
		
	}
	void mouseExited(MouseEvent e) {
		if(subScreen!=null){
			subScreen.mouseExited(e);
		}else{
			exited(e);
		}
	}
	public void exited(MouseEvent e){
		
	}
	void mousePressed(MouseEvent e) {
		if(subScreen!=null){
			subScreen.mousePressed(e);
		}else{
			pressed(e);
		}
	}
	public void pressed(MouseEvent e){
		
	}
	void mouseReleased(MouseEvent e) {
		if(subScreen!=null){
			subScreen.mouseReleased(e);
		}else{
			released(e);
		}
	}
	public void released(MouseEvent e){
		
	}
	void mouseMoved(MouseEvent e) {
		if(subScreen!=null){
			subScreen.mouseMoved(e);
		}else{
			moved(e);
		}
	}
	public void moved(MouseEvent e){
		
	}
	void mouseDragged(MouseEvent e) {
		if(subScreen!=null){
			subScreen.mouseDragged(e);
		}else{
			dragged(e);
		}
	}
	public void dragged(MouseEvent e){
		
	}

	
	
	
	public void setParentScreen(Screen parentScreen) {
		this.parentScreen = parentScreen;
	}

	public Screen getParentScreen() {
		return parentScreen;
	}
}
