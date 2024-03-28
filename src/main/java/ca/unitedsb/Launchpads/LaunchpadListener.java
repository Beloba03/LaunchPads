package ca.unitedsb.Launchpads;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class LaunchpadListener implements Listener {
    
    private final Set<UUID> onLaunchPad = new HashSet<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Block stoodUpon = player.getLocation().getBlock().getRelative(0, 0, 0);
        Block belowStoodUpon = stoodUpon.getRelative(0, -1, 0);
        
        // Check if the block below the player is a PRESSURE_PLATE and the block below that is a REDSTONE_BLOCK
        boolean isOnLaunchPadNow = stoodUpon.getType() == Material.STONE_PRESSURE_PLATE && belowStoodUpon.getType() == Material.REDSTONE_BLOCK;
        
        // Launch the player if they just stepped on the launchpad
        if (isOnLaunchPadNow && onLaunchPad.add(player.getUniqueId())) {
            // Customize the X, Y, and Z components of the launch velocity as needed
            double launchPowerY = 1.0; // Vertical launch power
            double launchPowerXZ = 4; // Horizontal launch power (applied in both X and Z directions to "forward" direction)
            Vector launchDirection = player.getLocation().getDirection().setY(0).normalize().multiply(launchPowerXZ).setY(launchPowerY);
            player.setVelocity(launchDirection);
            player.sendMessage("Woosh!");
        } else if (!isOnLaunchPadNow) {
            // Remove the player from the set when they step off the launchpad
            onLaunchPad.remove(player.getUniqueId());
        }
    }
}
