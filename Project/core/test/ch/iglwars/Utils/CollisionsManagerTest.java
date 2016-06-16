package ch.iglwars.Utils;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import ch.iglwars.TexturesMode.TextureMode;
import ch.iglwars.Utils.GraphicElement;
import ch.iglwars.Utils.CollisionsManager;

/**
 * Created by Loïc Viret on 01.06.16.
 *
 * Utilitaire offrant des méthodes pour les vérifications de collisions
 */
public class CollisionsManagerTest {

    @Test
    public void testIsColliding()
    {
        // Creation de deux elements graphiques qui se touchent
        GraphicElement element1 = new GraphicElement() {
            @Override
            protected TextureMode createTextureMode() {
                return null;
            }

            @Override
            protected void setPositionInLoop() {}

            @Override
            protected void setProperties() {
                // On set la position et la taille dans la fonction appelée par le constructeur
                setX(10);
                setY(10);
                setWidth(20);
                setHeight(20);
            }
        };


        GraphicElement element2 = new GraphicElement() {
            @Override
            protected TextureMode createTextureMode() {
                return null;
            }

            @Override
            protected void setPositionInLoop() {}

            @Override
            protected void setProperties() {
                // On set la position et la taille dans la fonction appelée par le constructeur
                setX(15);
                setY(15);
                setWidth(10);
                setHeight(10);
            }
        };

        assertTrue(CollisionsManager.isColliding(element1, element2));
    }

    @Test
    public void testIsNotColliding()
    {
        // Creation de deux elements graphiques qui ne se touchent pas
        GraphicElement element1 = new GraphicElement() {
            @Override
            protected TextureMode createTextureMode() {
                return null;
            }

            @Override
            protected void setPositionInLoop() {}

            @Override
            protected void setProperties() {
                // On set la position et la taille dans la fonction appelée par le constructeur
                setX(10);
                setY(10);
                setWidth(20);
                setHeight(20);
            }
        };


        GraphicElement element2 = new GraphicElement() {
            @Override
            protected TextureMode createTextureMode() {
                return null;
            }

            @Override
            protected void setPositionInLoop() {}

            @Override
            protected void setProperties() {
                // On set la position et la taille dans la fonction appelée par le constructeur
                setX(40);
                setY(40);
                setWidth(10);
                setHeight(10);
            }
        };

        assertFalse(CollisionsManager.isColliding(element1, element2));
    }
}
