package net.essentialsx.api.v2.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.earth2me.essentials.CommandSource;
import com.earth2me.essentials.Trade;
import com.earth2me.essentials.User;
import net.ess3.api.IEssentials;

/**
 * Fired when a shop transaction (e.g. sign sell or buy) is successfully handled.
 */
public class ShopTransactionEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private final CommandSource requester;
    private final User target;
    private final IEssentials ess;
    private Trade charge;
    private Trade money;
    private boolean cancelled = false;

    public ShopTransactionEvent(User target, Trade charge, Trade money, final IEssentials ess) {
        super(!Bukkit.isPrimaryThread());
        this.target = target;
        this.requester = target.getSource();
        this.charge = charge;
        this.money = money;
        this.ess = ess;
    }

    /**
     * @return the user who initiated the transaction
     */
    public CommandSource getRequester() {
        return requester;
    }

    /**
     * @return the user who received the money
     */
    public User getTarget() {
        return target;
    }

    /**
     * @return the Trade for the items transacted
     */
    public Trade getCharge() {
        return charge;
    }

    /**
     * @return the Trade for the money transacted
     */
    public Trade getMoney() {
        return money;
    }

    public IEssentials getEss() {
        return ess;
    }

    public void setCharge(Trade charge) {
        this.charge = charge;
    }

    public void setMoney(Trade money) {
        this.money = money;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
