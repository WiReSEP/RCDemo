/*
 * Copyright (C) 2016 ezander
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package rcdemo.physics;

import org.apache.commons.math3.linear.RealVector;

/**
 *
 * @author ezander
 */
public class FrictionForceModel implements ForceModel {

    @Override
    public RealVector getForce(RealVector x, RealVector v) {
        double rho = 1;
        double Cd = 1;
        double A = 1;
        double factor = 0.5 * rho * v.getNorm() * Cd * A;
        return v.mapMultiply(-factor);
    }

    @Override
    public double getPotentialEnergy(RealVector x, RealVector v) {
        // Frictional force is just lost, and not stored
        return 0;
    }
    
}
