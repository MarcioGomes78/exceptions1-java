package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;

	public Reservation() {
	}

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		if (!checkOut.after(checkIn)) {
			throw new DomainException ("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	/*
	 * Para que as datas não possam ser mudadas arbitrariamente public void
	 * setCheckIn(Date checkIn) { this.checkIn = checkIn; }
	 */

	public Date getCheckOut() {
		return checkOut;
	}

	/*
	 * public void setCheckOut(Date checkOut) { this.checkOut = checkOut; }
	 */

	// Metodo para calcular a duração
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		// Para converter o diff de milliseconds para days
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	// Metodo para atualizar checkin e checkOut.
	public void updateDates(Date checkIn, Date checkOut) {
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Reservation dates for update must be future dates");
		}
		if (!checkOut.after(checkIn)) {
			throw new DomainException ("Check-out date must be after check-in date");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	@Override
	public String toString() {
		return "Room " + roomNumber + ", check-in: " + sdf.format(checkIn) + ", ckeck-out: " + sdf.format(checkOut)
				+ ", " + duration() + " nights";
	}
}
